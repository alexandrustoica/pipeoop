import time
import logging

from pyspark import SparkContext, SparkConf
from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement
from cassandra import ConsistencyLevel

conf = SparkConf().setAppName("test").setMaster("local[*]")
sc = SparkContext(conf=conf)

result = sc.textFile("hdfs://10.111.0.250/team_3/data/workunit") \
    .map(lambda x: x.split(",")) \
    .map(lambda x: x[0]) \
    .count()

print(result)

print("Waiting 5s to connect to Cassandra database...")
time.sleep(5)

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

log.info("Create table if needed...")

session.execute('CREATE TABLE IF NOT EXISTS result (result int primary key)')

# TODO: CHECK WHY THIS IS NOT WORKING
session.execute("""
    INSERT INTO result (result)
    VALUES (%d)
    """, (result[0]))

for x in session.execute('SELECT * FROM result'):
    print(x)
