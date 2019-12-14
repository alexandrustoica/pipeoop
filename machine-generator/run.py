import random

from pony import orm
from pony.orm import Required, PrimaryKey, db_session, select, count
from datetime import datetime

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
def create_work_unit(units_of_work_created: int):
    if units_of_work_created % 100 == 0:
        unit = WorkUnit(batch_id=1,
                        timestamp=datetime.now(),
                        tool_id="TID_a",
                        cup_pressure_lower_limit=0.3,
                        cup_pressure_upper_limit=0.5,
                        air_cap_pressure_lower_limit=0.21,
                        air_cap_pressure_upper_limit=0.63)


if __name__ == '__main__':
    database.bind(provider='mysql', host='192.168.0.26', port=13306, user='root', passwd='password', db='msg')
    database.generate_mapping(create_tables=True)
    units_of_work_created = 0
    with db_session:
        batch_id = WorkUnit.select().count() // 1000 + 1
        while True:
            if batch_id % 1000 == 0:
                batch_id += 1
            cup_pressure_lower_limit = random.uniform(0, 0.5)
            air_cap_pressure_lower_limit = random.uniform(0, 0.5)
            unit = WorkUnit(batch_id=batch_id,
                            timestamp=datetime.now(),
                            tool_id="TID_a",
                            cup_pressure_lower_limit=cup_pressure_lower_limit,
                            cup_pressure_upper_limit=cup_pressure_lower_limit + random.uniform(0, 0.4),
                            air_cap_pressure_lower_limit=air_cap_pressure_lower_limit,
                            air_cap_pressure_upper_limit=air_cap_pressure_lower_limit + random.uniform(0, 0.4))
            database.commit()
            print("Creating unit of work with id = {}".format(str(unit.part_id)))
