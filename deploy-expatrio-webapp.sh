#!/bin/zsh
./gradlew build
docker build -t expatrio-webapp-image .
docker-compose up
