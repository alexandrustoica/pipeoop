#### How To Run

A kafka message broker requires a couple of properties to work.

Step#1: Create a topic (if is not already created).

```
$ kafka-topics --create --zookeeper 0.0.0.0:2181 --replication-factor 1 --partitions 2 --topic topicteam_3sensorpressure
```

Step#2: Start kafka mqtt message broker. 

```
$ export JAVA_HOME=/usr
$ kafka-mqtt-start pipeoop/kafka-mqtt-configuration/kafka-mqtt-team.properties
```

Step#3: Read submitted data with kafka consumer.
```
$ export JAVA_HOME=/usrz
$ kafka-console-consumer --bootstrap-server localhost:9092 --topic topicteam_3sensorpressure --from-beginning
```
