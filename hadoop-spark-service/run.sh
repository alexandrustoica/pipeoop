#!/bin/bash

sleep 5
sqoop import --connect jdbc:mysql://"$DB_HOST":"$DB_PORT"/"$DB_DATABASE" --username "$DB_USERNAME" \
 --password "$DB_PASSWORD" --table workunit --m 1 --incremental append --check-column part_id
