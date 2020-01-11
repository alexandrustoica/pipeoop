import paho.mqtt.client as mqtt

def on_message(client, obj, msg):
	print(str(msg.payload))

mqttc = mqtt.Client()
mqttc.on_message = on_message

mqttc.connect = ('localhost', 1883)
mqttc.subscribe('test', 0)
mqttc.publish('test', 'test -msg')
rc = 0
while rc == 0:
	rm = mqttc.loop()

