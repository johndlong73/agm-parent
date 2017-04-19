# agm-parent

## Intro

This REST service is a stub.  It returns a hard coded list of sensor data.  Its purpose is to allow early development of the client while the service is being developed.

## How to build

CD to sensor-history and run mvn clean install

## How to run

In sensor-history run java -jar target/sensor-history-0.1.0.jar

## Hot to test

Using your favorite REST client do HTTP GET http://localhost:8080/v1.0/12345/sensorHistory?deviceId=1234
