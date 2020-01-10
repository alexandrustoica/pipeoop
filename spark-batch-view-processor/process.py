import time
import logging

from pyspark import SparkContext, SparkConf
from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement
from cassandra import ConsistencyLevel
from functools import reduce

conf = SparkConf().setAppName("test").setMaster("local[*]")
sc = SparkContext(conf=conf)


class ErrorCounter(object):

    def __init__(self, first_type: int, second_type: int, third_type: int):
        self.first_type = first_type
        self.second_type = second_type
        self.third_type = third_type

    def __repr__(self):
        return "ErrorCounter(type_1 = {}, type_2 = {}, type_3 = {})" \
            .format(self.first_type, self.second_type, self.third_type)


class BatchErrorCounter(object):

    def __init__(self, batch_id: int, error_counter: 'ErrorCounter'):
        self.batch_id = batch_id
        self.error_counter = error_counter

    def __repr__(self):
        return "BatchErrorCounter(batch_id = {}, errors = {})" \
            .format(self.batch_id, repr(self.error_counter))


class BatchToolTypeErrorCounter(object):

    def __init__(self, batch_id: int,
                 type_a_error_counter: int,
                 type_b_error_counter: int):
        self.batch_id = batch_id
        self.type_a_error_counter = type_a_error_counter
        self.type_b_error_counter = type_b_error_counter

    def __repr__(self):
        return "BatchToolTypeErrorCounter(batch_id = {}," \
               " type_a_errors = {}, type_b_errors = {})" \
            .format(self.batch_id,
                    self.type_a_error_counter,
                    self.type_b_error_counter)


class AvgPressure(object):
    def __init__(self, pressure: int, lower_limit: int, upper_limit: int):
        self.pressure = pressure
        self.lower_limit = lower_limit
        self.upper_limit = upper_limit

    def __repr__(self):
        return "AvgPressure=(pressure = {}, lower_limit = {}, upper_limit = {})" \
            .format(self.pressure, self.lower_limit, self.upper_limit)


class BatchAvgPressure(object):

    def __init__(self, batch_id: int,
                 avg_cup_pressure: 'AvgPressure',
                 avg_air_pressure: 'AvgPressure',
                 total_pieces: int):
        self.batch_id = batch_id
        self.avg_cup_pressure = avg_cup_pressure
        self.avg_air_pressure = avg_air_pressure
        self.total_pieces = total_pieces

    def __repr__(self):
        return "BatchAvgPressure(batch_id = {}, \n\t\t avg_cup_pressure = {}, \n\t\t " \
               "avg_air_pressure = {}, \n\t\t total = {})" \
            .format(self.batch_id, repr(self.avg_cup_pressure),
                    repr(self.avg_air_pressure), self.total_pieces)


work_units = sc.textFile("hdfs://10.111.0.250/team_3/data/workunit") \
    .map(lambda x: x.split(","))

count_operator_type_a = lambda x, y: (x[0] + 1, x[1]) if y == 'TID_A' else (
    x[0], x[1] + 1)
add_operator = lambda x, y: (x[0] + y[0], x[1] + y[1])

bv_count_by_type = work_units \
    .map(lambda x: (int(x[1]), x[3])) \
    .aggregateByKey((0, 0), count_operator_type_a, add_operator) \
    .collect()

count_errors_by_type = [
    BatchToolTypeErrorCounter(batch_id, type_a_counter, type_b_counter)
    for (batch_id, (type_a_counter, type_b_counter)) in bv_count_by_type]

[print(x) for x in count_errors_by_type]

operator = lambda index: lambda acc, it: acc + float(it[index])
bv_avg_by_pressure = work_units \
    .map(lambda x: (x[1], x)) \
    .groupByKey(1) \
    .map(lambda batch: (batch[0], batch[1], len(batch[1]))) \
    .map(lambda x:
         (x[0], (reduce(operator(4), x[1], 0) / x[2],
                 reduce(operator(5), x[1], 0) / x[2],
                 reduce(operator(6), x[1], 0) / x[2],
                 reduce(operator(7), x[1], 0) / x[2],
                 reduce(operator(8), x[1], 0) / x[2],
                 reduce(operator(9), x[1], 0) / x[2]
                 ), x[2])) \
    .collect()

avg_by_pressure = [BatchAvgPressure(
    batch_id,
    AvgPressure(cup_pressure, lower_cup_pressure, upper_cup_pressure),
    AvgPressure(air_pressure, lower_air_pressure, upper_air_pressure), total)
    for (batch_id, (cup_pressure, lower_cup_pressure, upper_cup_pressure,
                    air_pressure, lower_air_pressure, upper_air_pressure),
         total) in bv_avg_by_pressure]

[print(x) for x in avg_by_pressure]

errors = sc.textFile("hdfs://10.111.0.250/team_3/data/pressure_error") \
    .map(lambda x: x.split(","))

work_units_with_errors = work_units.map(lambda x: (x[0], x)).join(
    errors.map(lambda x: (x[1], x)))

count_operator = lambda x, y: (x[0] + 1, x[1], x[2]) if y == '1' \
    else (x[0], x[1] + 1, x[2]) if y == '2' \
    else (x[0], x[1], x[2] + 1)

comb_operator = lambda x, y: (x[0] + y[0], x[1] + y[1], x[2] + y[2])

bv_error_count = work_units_with_errors \
    .map(lambda x: (x[1][0][1], x[1][1][2])) \
    .aggregateByKey((0, 0, 0), count_operator, comb_operator) \
    .collect()

error_count = [BatchErrorCounter(batch_id, ErrorCounter(type_1, type_2, type_3))
               for (batch_id, (type_1, type_2, type_3)) in bv_error_count]

[print(x) for x in error_count]

# print("Waiting 5s to connect to Cassandra database...")
# time.sleep(5)

logging.basicConfig(level=logging.INFO)
log = logging.getLogger()

cluster = Cluster(['cassandra'], port=9042)
session = cluster.connect()

log.info("Create keyspace if needed...")

session.execute("""
       CREATE KEYSPACE IF NOT EXISTS msg
       WITH REPLICATION =
       { 'class' : 'SimpleStrategy', 'replication_factor' : '1' }
       """)

for x in session.execute('SELECT * FROM system_schema.keyspaces'):
    print(x)

session.set_keyspace('msg')

session.execute("""
    CREATE TABLE IF NOT EXISTS BatchViewErrorCounter (
        batch_id int primary key,
        type_1_counter int,
        type_2_counter int, 
        type_3_counter int
    )
    """)

for error in error_count:
    session.execute("""
        INSERT INTO BatchViewErrorCounter 
        (batch_id, type_1_counter, type_2_counter, type_3_counter)
        VALUES (%s, %s, %s, %s)
        """, (int(error.batch_id),
              int(error.error_counter.first_type),
              int(error.error_counter.second_type),
              int(error.error_counter.third_type)))

session.execute("""
    CREATE TABLE IF NOT EXISTS BatchAvgPressure (
        batch_id int primary key,
        cup_pressure float,
        lower_cup_pressure float,
        upper_cup_pressure float,
        air_pressure float, 
        lower_air_pressure float, 
        upper_air_pressure float, 
        total int
    )
    """)

for entry in avg_by_pressure:
    session.execute("""
        INSERT INTO BatchAvgPressure 
        (batch_id, cup_pressure, lower_cup_pressure, upper_cup_pressure, 
        air_pressure, lower_air_pressure, upper_air_pressure, total)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
        """, (int(entry.batch_id),
              float(entry.avg_cup_pressure.pressure),
              float(entry.avg_cup_pressure.lower_limit),
              float(entry.avg_cup_pressure.upper_limit),
              float(entry.avg_air_pressure.pressure),
              float(entry.avg_air_pressure.lower_limit),
              float(entry.avg_air_pressure.upper_limit),
              int(entry.total_pieces)))

session.execute("""
    CREATE TABLE IF NOT EXISTS BatchToolTypeErrorCounter (
        batch_id int primary key,
        type_a_errors int,
        type_b_errors int
    )
    """)

for entry in count_errors_by_type:
    session.execute("""
        INSERT INTO BatchToolTypeErrorCounter 
        (batch_id, type_a_errors, type_b_errors)
        VALUES (%s, %s, %s)
        """, (int(entry.batch_id),
              int(entry.type_a_error_counter),
              int(entry.type_b_error_counter)))


for x in session.execute('SELECT * FROM BatchViewErrorCounter'):
    print(x)

for x in session.execute('SELECT * FROM BatchAvgPressure'):
    print(x)

for x in session.execute('SELECT * FROM BatchToolTypeErrorCounter'):
    print(x)