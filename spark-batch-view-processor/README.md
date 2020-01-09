##### How To Run (Remote Server)

```
hdfs dfs -rm -r /team_3/result ; spark-submit pipeoop/spark-batch-view-processor/process.py
```

##### How To Run (Local)

```
docker build . -t spark-batch-view:new
docker run -it -p 8888:8888 spark-batch-view:new
```
