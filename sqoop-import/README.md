### Sqoop Import

Step 1. View all files stored in `HDFS`

```commandline
hadoop fs -ls /
```

Step 2. Test connection between sqoop and mysql service.

 ```
sqoop list-tables \
--connect ”jdbc:mysql://localhost:3306/msg” \
--username=root \
--password=cloudera
```

Step 3. Create a file `password` and move it to `HDFS` (used by `sqoop` when executing job).

```
echo -n 'password' > sqoop.password; hadoop fs -put $(pwd)/sqoop.password /team_3/sqoop.password
```

Step 4. Create a sqoop job to import all tables from mysql database.
> TODO: Import `--increment` each table.
```
sqoop job --create mysql_team_3 -- import-all-tables \
 --connect jdbc:mysql://localhost:3306/msg \
 --username root --password-file /user/root/sqoop.password --warehouse-dir '/team_3/data/'
```

Step 5. Check if the job was created.
```
sqoop job --list
```
 
Step 6. Execute sqoop job to make sure everything works.
 
```
hadoop fs -rm -r -skipTrash /team_3/data/pressureerror; \
hadoop fs -rm -r -skipTrash /team_3/data/workunit; \
sqoop job -exec mysql_team_3
```

Step 7. Check if everything is imported.

```
hadoop fs -ls /team_3/data
```

Step 8. Check a file's content:
```
hadoop fs -cat /team_3/data/pressureerror/part-m-*
```

Step 9. Create cron job to automate the running our sqoop job.
> TODO: Create Script For This & Batch View Generator 
```
0 0 * * * [user] \
/usr/bin/hadoop fs -rm -r -skipTrash /user/root/pressureerror; \
/usr/bin/hadoop fs -rm -r -skipTrash /user/root/workunit; \
/usr/bin/sqoop job -exec mysql_team_3
```
