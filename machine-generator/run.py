import random

from pony import orm
from pony.orm import Required, PrimaryKey, db_session, Optional
from datetime import datetime
import time
import os
import logging

database = orm.Database()


class Pressure(database.Entity):
    id = PrimaryKey(int, auto=True)
    value = Required(float)
    lower_limit = Required(float)
    upper_limit = Required(float)
    cup_work_unit = Optional("WorkUnit", reverse="cup_pressure")
    air_work_unit = Optional("WorkUnit", reverse="air_cap_pressure")


class WorkUnit(database.Entity):
    part_id = PrimaryKey(int, auto=True)
    batch_id = Required(int)
    timestamp = Required(datetime)
    tool_id = Required(str)
    cup_pressure = Required(Pressure)
    air_cap_pressure = Required(Pressure)
    error = Optional("PressureError")


class PressureError(database.Entity):
    id = PrimaryKey(int, auto=True)
    work_unit = Required("WorkUnit")
    code = Required(int)


@db_session
def run():
    # TODO: Refactor and improve error generator
    batch_id = WorkUnit.select().count() // 1000 + 1
    while True:
        logging.warning("Painting a new unit ...")
        time.sleep(int(os.getenv('TIME_PRINTING')))
        cup_pressure_lower_limit = random.uniform(0, 0.5)
        air_cap_pressure_lower_limit = random.uniform(0, 0.5)
        cup_pressure = Pressure(
            value=random.uniform(0, 1),
            lower_limit=cup_pressure_lower_limit,
            upper_limit=random.uniform(0, 0.4) + cup_pressure_lower_limit
        )
        air_cap_pressure = Pressure(
            value=random.uniform(0, 1),
            lower_limit=air_cap_pressure_lower_limit,
            upper_limit=random.uniform(0, 0.4) + air_cap_pressure_lower_limit
        )
        unit = WorkUnit(
            batch_id=batch_id,
            timestamp=datetime.now(),
            tool_id="TID_a",
            cup_pressure=cup_pressure,
            air_cap_pressure=air_cap_pressure)
        error_code = 1 \
            if cup_pressure.value < cup_pressure.lower_limit or \
               cup_pressure.value > cup_pressure.upper_limit else 0 + 2 \
            if air_cap_pressure.value < air_cap_pressure.lower_limit or \
               air_cap_pressure.value > air_cap_pressure.upper_limit else 0
        if error_code != 0:
            error = PressureError(work_unit=unit, code=error_code)
            logging.error("Error {} while painting {} ...".format(
                str(error.code), str(unit.part_id)))
        database.commit()
        logging.warning("Painted unit of work with id = {} cup pressure = {} air cap pressure = {} "
                        .format(str(unit.part_id), str(unit.cup_pressure.value), str(unit.air_cap_pressure.value)))
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
