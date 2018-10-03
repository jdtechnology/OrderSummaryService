#!/usr/bin/env bash

if [ -e  target/order-summary-test-1.0-SNAPSHOT-jar-with-dependencies.jar ]; then
    java -jar target/order-summary-test-1.0-SNAPSHOT-jar-with-dependencies.jar
else
    mvn package
    java -jar target/order-summary-test-1.0-SNAPSHOT-jar-with-dependencies.jar
fi
