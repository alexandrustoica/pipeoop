import paho.mqtt.client as mqtt
import logging

def print_connected():
    logging.info("Connected")


def print_subscribed():
    logging.info("Subscribed")


remote_mqtt_connection = mqtt.Client()

remote_mqtt_connection.on_connect = print_connected
remote_mqtt_connection.connect("10.111.0.250", 1883)


def on_message(client, obj, msg):
    remote_mqtt_connection.publish(
        "topicteam_3sensorpressure", str(msg.payload))
    logging.info("Topic: {} Payload: {}"
                 .format(str(msg.topic), str(msg.payload)))


local_mqtt_connection = mqtt.Client()
local_mqtt_connection.on_message = on_message
local_mqtt_connection.on_connect = print_connected
local_mqtt_connection.on_subscribe = print_subscribed

local_mqtt_connection.connect("192.168.0.31", 1883)
local_mqtt_connection.subscribe("topic/team_3/sensor/pressure", 0)

rc = 0
while rc == 0:
    rc = local_mqtt_connection.loop()
print("Disconnected!")
