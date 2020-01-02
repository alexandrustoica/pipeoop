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

4. Create a file `password` and move it to `HDFS` (used by `sqoop` when executing job).

```
echo -n 'password' > sqoop.password; hadoop fs -put /sqoop.password /user/root/sqoop.password
```

> Note: Make sure to add a target directory if you're running this job on UBB.
5. Create a sqoop job to import all tables from mysql service.

```
sqoop job --create mysql -- import-all-tables \
 --connect jdbc:mysql://<mysql_ip>:<port>/msg \
 --username root --password-file /user/root/sqoop.password
```

6. Check if the job was created.
```
sqoop job --list
```

> Note: You need to delete /user/root/<target-dirs to be able to execute this command multiple times.
7. Execute sqoop job to make sure everything works.
 
```
hadoop fs -rm -r -skipTrash /user/root/pressureerror; \
hadoop fs -rm -r -skipTrash /user/root/workunit; \
sqoop job -exec mysql
```

8. Check if everything is imported.

```
hadoop fs -ls /user/root
```

9. Check a file's content:
```
hadoop fs -cat /user/root/pressureerror/part-m-*
```

10. Create cron job to automate the running our sqoop job.
# TODO: Check why cron job is not working...
```
0 0 * * * [user] \
/usr/bin/hadoop fs -rm -r -skipTrash /user/root/pressureerror; \
/usr/bin/hadoop fs -rm -r -skipTrash /user/root/workunit; \
/usr/bin/sqoop job -exec mysql
```