#### How To Run

A kafka message broker requires a couple of properties to work.

Step#1: Create a topic (if is not already created).

```
$ kafka-topics --create --zookeeper 0.0.0.0:2181 --replication-factor 1 --partitions 2 --topic <topic>
```

Step#2: Start kafka mqtt message broker. 

```
$ export JAVA_HOME=/usr
$ kafka-mqtt-start pipeoop/kafka-mqtt-configuration/kafka-mqtt-team.properties
```
