### Sqoop Import

1. Connect to `HDFS` container.
```
docker exec -it $(docker container ls | grep cloudera | cut -d " " -f1) bash
```

2. View all files stored in `HDFS`
```commandline
hadoop fs -ls
```

3. Test connection between sqoop and mysql service.

 ```
sqoop list-tables =\
--connect ”jdbc:mysql://<mysql_ip>:13306/msg” \
--username=root \
--password=password
```

> Note: Make sure Docker has 8gb of RAM memory and disk space.

4. Manually import all tables with sqoop.
```
sqoop import-all-tables \
--connect ”jdbc:mysql://<mysql_ip>:3306/msg” \
--username=root \
--password=password \
--warehouse-dir <dir_name>
```

5. Check if everything is imported.

```
hadoop fs -ls <dir_name>
```

6. Check a file's content:
```
hadoop fs -cat <dir_name>/<table_name>/part-m-*
```