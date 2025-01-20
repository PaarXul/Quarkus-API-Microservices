#!/bin/bash

# Limpiar y detener contenedores previos
docker-compose down
docker-compose rm -f

# Compilar task-service
echo "Compilando task-service..."
cd task-service
./mvnw clean package -DskipTests
cd ..

# Compilar category-service
echo "Compilando category-service..."
cd category-service
./mvnw clean package -DskipTests
cd ..

# Compilar category-service
echo "Compilando category-service..."
cd api-gateway
./mvnw clean package -DskipTests
cd ..

# Construir y ejecutar con docker-compose
echo "Iniciando servicios con docker-compose..."
docker-compose up --build