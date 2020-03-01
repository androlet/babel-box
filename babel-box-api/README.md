#Babel-box-api

## Install

- Run `mvn install`.
- Install PostgreSQL and create "babelboxdev" database
- Run backend app (hibernate tables creation)
- Execute _/babel-box-api/src/main/resources/import.sql_ into "babelboxdev" database.
- Run again backend app

## Run

- Run `mvn spring-boot:run -Dspring.profiles.active=server`.

## Test

- Run `mvn test`.