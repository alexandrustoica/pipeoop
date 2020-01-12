### How To Run

#### Easy Docker Approach

```
$ docker-compose build; docker-compose up
```

#### Manual Approach

Step#1: Install `mosquitto` locally.

```
$ brew install mosquitto
```

Step#2: Start `mosquitto` broker.

```
$ /usr/local/sbin/mosquitto -c /usr/local/etc/mosquitto/mosquitto.conf
```

Step#2.1: Start subscribing to a topic. [optional]
```
$ mosquitto_sub -h <localhost> -p 1883 -t test
```

Step#3: Install python dependencies.

```
$ pip3 install -r requirements.txt
```

Step#4: Run run.py

```
$ python run.py
```
