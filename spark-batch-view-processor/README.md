##### How To Run (Remote Server)

```
hdfs dfs -rm -r /team_3/result ; spark-submit pipeoop/spark-batch-view-processor/process.py
```

##### How To Run (Local)

```
docker build . -t spark-batch-view:new
docker run -it -p 8888:8888 spark-batch-view:new
```

##### How To Test Things

Step#1: Start a pyspark notebook docker container on port 8888.

```
docker run -it --rm -p 8888:8888 jupyter/pyspark-notebook
```

Step#2: Go to [http://127.0.0.1:8888/?token...](http://localhost:8888) and login.
