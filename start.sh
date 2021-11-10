#!/bin/sh

mvn clean install

docker build -t vitamin/stock-analysis-service .

docker run -p 8080:8080 vitamin/stock-analysis-service
