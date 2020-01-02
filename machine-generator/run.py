import random

from pony import orm
from pony.orm import Required, PrimaryKey, db_session, Optional
from datetime import datetime
import time
import os
import logging

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
def run():
    batch_size = int(os.getenv('BATCH_SIZE'))
    batch_id = WorkUnit.select().count() // batch_size + 1
    lower_cup_pressure, upper_cup_pressure = \
        0.5 - random.uniform(0, 0.5), 1 - random.uniform(0, 0.5)
    lower_air_pressure, upper_air_pressure = \
        0.5 - random.uniform(0, 0.5), 1 - random.uniform(0, 0.5)
    while True:
        logging.warning("Painting a new unit ...")
        time.sleep(int(os.getenv('TIME_PRINTING')))
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
            logging.error("Error {} while painting {} ...".format(
                str(error.code), str(unit.part_id)))
        database.commit()
        logging.warning("Painted unit of work with id = {} cup pressure = {} air cap pressure = {} "
                        .format(str(unit.part_id), str(unit.cup_pressure), str(unit.air_pressure)))
        if unit.part_id % batch_size == 0:
            batch_id += 1


if __name__ == '__main__':
    logging.warning("Waiting for mysql service to configure (10s) ...")
    time.sleep(10)
    database.bind(
        provider='mysql',
        host=os.getenv('DB_HOST'),
        port=int(os.getenv('DB_PORT')),
        user=os.getenv('DB_USERNAME'),
        passwd=os.getenv('DB_PASSWORD'),
        db=os.getenv('DB_DATABASE'))
    database.generate_mapping(create_tables=True)
    run()
