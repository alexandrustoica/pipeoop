import logging

from cassandra.cluster import Cluster

logging.basicConfig(level=logging.INFO)

cluster = Cluster(['192.168.0.26'], port=9042)
session = cluster.connect()
session.execute("""
       CREATE KEYSPACE IF NOT EXISTS test2
       WITH REPLICATION =
       { 'class' : 'SimpleStrategy', 'replication_factor' : '1' }
       """)
for x in session.execute('select * from system_schema.keyspaces'):
    print(x)

session.set_keyspace('test')
rows = session.execute('SELECT id FROM test')
for id in rows:
    print(id)
