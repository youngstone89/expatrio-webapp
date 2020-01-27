#!/bin/zsh
echo "Stopping and removing expatrio-webapp..."
docker-compose down

echo "Removing postgresl container volume..."
docker volume rm webapp_postgres-data

echo "Check volume list"
docker volume ls 
