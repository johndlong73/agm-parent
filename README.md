# agm-parent

## Intro

This project contains two modules, a RESTful service and a Spring WebMvc application.  The WebMvc application, called msr (multiple sensor report), hosts the getReport endpoint where the end user can get their Multiple Sensor Report.

The RESTful service, called SensorHistory, returns historical sensor data from a customers device. 

## How to build

### Building the Applications
CD to agm-parent and run mvn clean install

### Building the Databases
This project includes tooling for managing local developer databases.  You'll need to have installed MySQL server on your machine and it is helpful if the root user's password is left blank to avoid having to tweak configuration files.

The database automation is built on top of DBMaintain (http://www.dbmaintain.org/overview.html), which allows you to manage your database schema as code.  The DBMaintain maven plugin is used to make using DBMaintain easy.  There are also wrapper scripts included in the project to make things even easier.

Here are the instructions for building the databases from scratch.  Once complete you'll have three databases running in your MySQL server, aesadmin, c00001, and c00002.  The aesadmin is the admin database.  The c00001 and c00002 databases are the sensor_history databases for two test customers c00001 and c00002.

1. CD to agm-parent/aesadmin-sqldb
1. run ./builddb
1. CD to agm-parent/sensorhistory-sqldb
1. run ./builddb
1. run ./builddb -Dproperties.file=src/main/config/c00002-sensorhistory.properties

Notice on the last command we added the -D switch?  That is because we are overriding the default value for the properties.file property and pointing it to a non default file.  These properties files contain connection string information and a pointer to a datafile which can be used to load seed data into your database.

There are other useful scripts in each of the sqldb projects.

* destroydb - Destroys a database by deleting it from the server
* rebuilddb - Destroy and then rebuild a database.  Same as calling destroydb followed by builddb
* populatedb - Load data into a database
* updatedb - Updates a database schema by applying the incremental change files that haven't been applied yet.  DBMaintain keeps track of which incremental change files have been applied. 

## How to run
Start your local database first.  Be sure to build it if you haven't done so already using the instructions above.

Start SensorHistory service
From the agm-parent folder run: java -jar sensor-history/target/sensor-history-0.1.0.jar

Start msr
From the agm-parent folder run: java -jar msr/target/msr-0.1.0.jar

Where 0.1.0 is the version of the jar.


## How to test

Test SensorHistory:
Using your favorite REST client do HTTP GET http://localhost:8090/v1.0/1001/sensorHistory?timestamp=${long}&deviceId=206&sensorTypes=WEIGHT_4,TOTAL_CONDUCTIVITY&startDate=2017-04-16T17:00:00.000&endDate=2017-04-16T21:00:00.000

You should get a small collection of sensor data.

Test msr:
Using your web browser hit http://localhsot:8080/getReport

You should get a page that reads Multiple Sensor Report UI Goes Here!