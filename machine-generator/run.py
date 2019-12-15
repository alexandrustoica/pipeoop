import random

from pony import orm
from pony.orm import Required, PrimaryKey, db_session
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
    cup_pressure_lower_limit = Required(float)
    cup_pressure_upper_limit = Required(float)
    air_cap_pressure_lower_limit = Required(float)
    air_cap_pressure_upper_limit = Required(float)


@db_session
def run():
    batch_id = WorkUnit.select().count() // 1000 + 1
    while True:
        logging.warning("Painting a new unit ...")
        time.sleep(int(os.getenv('TIME_PRINTING')))
        cup_pressure_lower_limit = random.uniform(0, 0.5)
        air_cap_pressure_lower_limit = random.uniform(0, 0.5)
        unit = WorkUnit(
            batch_id=batch_id,
            timestamp=datetime.now(),
            tool_id="TID_a",
            cup_pressure_lower_limit=cup_pressure_lower_limit,
            cup_pressure_upper_limit=cup_pressure_lower_limit + random.uniform(0, 0.4),
            air_cap_pressure_lower_limit=air_cap_pressure_lower_limit,
            air_cap_pressure_upper_limit=air_cap_pressure_lower_limit + random.uniform(0, 0.4))
        database.commit()
        logging.warning("Painted unit of work with id = {} cup pressure = ({}, {}) air cap pressure = ({}, {})".format(
            str(unit.part_id), str(unit.cup_pressure_lower_limit), str(unit.cup_pressure_upper_limit),
            str(unit.air_cap_pressure_lower_limit), str(unit.air_cap_pressure_upper_limit)))
        if unit.part_id % 1000 == 0:
            batch_id += 1


if __name__ == '__main__':
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
