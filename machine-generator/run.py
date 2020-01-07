import getopt
import random
import sys

from pony import orm
from pony.orm import Required, PrimaryKey, db_session, Optional
from datetime import datetime
import time

database = orm.Database()


class WorkUnit(database.Entity):
    part_id = PrimaryKey(int, auto=True)
    batch_id = Required(int)
    timestamp = Required(datetime)
    tool_id = Required(str)
    cup_pressure = Required(float)
    lower_cup_pressure = Required(float)
    upper_cup_pressure = Required(float)
    air_pressure = Required(float)
    lower_air_pressure = Required(float)
    upper_air_pressure = Required(float)
    error = Optional("PressureError")

    @property
    def error_code(self) -> int:
        return 0 if self.lower_cup_pressure < self.cup_pressure < self.upper_cup_pressure else 1 + 0 \
            if self.lower_air_pressure < self.air_pressure < self.upper_air_pressure else 2


class PressureError(database.Entity):
    id = PrimaryKey(int, auto=True)
    work_unit = Required("WorkUnit")
    code = Required(int)


@db_session
def generate(batch_size: int, delay: int):
    batch_id = WorkUnit.select().count() // batch_size + 1
    lower_cup_pressure, upper_cup_pressure = \
        0.5 - random.uniform(0, 0.5), 1 - random.uniform(0, 0.5)
    lower_air_pressure, upper_air_pressure = \
        0.5 - random.uniform(0, 0.5), 1 - random.uniform(0, 0.5)
    while True:
        print("-" * 100)
        print("Painting a new unit ...")
        time.sleep(delay)
        unit = WorkUnit(
            batch_id=batch_id,
            timestamp=datetime.now(),
            tool_id=random.choice(["TID_A", "TID_B"]),
            cup_pressure=random.uniform(0, 1),
            air_pressure=random.uniform(0, 1),
            lower_cup_pressure=lower_cup_pressure,
            upper_cup_pressure=upper_cup_pressure,
            lower_air_pressure=lower_air_pressure,
            upper_air_pressure=upper_air_pressure)
        if unit.error_code != 0:
            error = PressureError(work_unit=unit, code=unit.error_code)
            print("Error {} while painting {} ...".format(
                str(error.code), str(unit.part_id)))
        database.commit()
        print("Painted unit of work with id = {} cup pressure = {} air cap pressure = {} "
              .format(str(unit.part_id), str(unit.cup_pressure), str(unit.air_pressure)))
        if unit.part_id % batch_size == 0:
            batch_id += 1


def main(argv):
    try:
        options, args = getopt.getopt(
            argv, "b:t:", ["host=", "database=", "port=", "username=", "password="])
        configuration = {key: arg for key, arg in options}
        print("Connecting to database {--database} on {--host}:{--port} with credentials.".format(**configuration))
        database.bind(
            provider='mysql',
            host=configuration["--host"],
            port=int(configuration["--port"]),
            user=configuration["--username"],
            passwd=configuration["--password"],
            db=configuration["--database"])
        database.generate_mapping(create_tables=True)
        print("Connected!")
        generate(int(configuration["-b"]), int(configuration["-t"]))
    except getopt.GetoptError:
        print("Unrecognised options ...")


if __name__ == '__main__':
    main(sys.argv[1:])
