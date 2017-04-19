# agm-parent

## Intro

This project contains two modules, a RESTful service and a Spring WebMvc application.  The WebMvc application, called msr (multiple sensor report), hosts the getReport endpoint where the end user can get their Multiple Sensor Report.

The RESTful service, called SensorHistory, returns historical sensor data from a customers device. 

## How to build

CD to agm-parent and run mvn clean install

## How to run

Start SensorHistory service
From the agm-parent folder run: java -jar sensor-history/target/sensor-history-0.1.0.jar

Start msr
From the agm-parent folder run: java -jar msr/target/msr-0.1.0.jar

Where 0.1.0 is the version of the jar.


## How to test

Test SensorHistory:
Using your favorite REST client do HTTP GET http://localhost:8090/v1.0/12345/sensorHistory?deviceId=1234

You should get a small collection of sensor data that has been mocked up.

Test msr:
Using your web browser hit http://localhsot:8080/getReport

You should get a page that reads Multiple Sensor Report UI Goes Here!