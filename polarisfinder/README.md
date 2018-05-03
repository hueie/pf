## Development Environment

- MySQL: 5.7
- JDK: 1.8.0_151
- Spring Tool Suite: 3.8.4.RELEASE
- STS Plugin : Dashboard -> Manage -> IDE Extension -> Gradle (STS Legacy) Support
- Project Right Click -> Configure -> Convert to Gradle Project
- Run As -> Spring boot App

## Installation

Mysql DB Dump

src/main/resources/static/db/Dumpxxxxxxxx.sql

Change application.properties

src/main/resources/static/application.properties

## Deployment

> $ cd $PROJECT_LOCATION

> $ gradlew clean build

> $ cd build

> $ cp . $DEPLOY_LOCATION

> $ cd $DEPLOY_LOCATION

> $ nohup java -jar libs/polarxy-0.1.0.jar &



