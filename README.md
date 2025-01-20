# TODO List Microservices con Quarkus

Este proyecto consiste en una aplicación de lista de tareas implementada con microservicios utilizando Quarkus, Docker y Docker Compose.

## Requisitos

- Docker
- Docker Compose
- Maven

## Servicios

El proyecto está compuesto por los siguientes servicios:

- **task-service**: Servicio para gestionar tareas.
- **category-service**: Servicio para gestionar categorías.
- **api-gateway**: Puerta de enlace para acceder a los servicios.

## Instrucciones para ejecutar el proyecto

1. Clonar el repositorio:

    ```bash
    git clone git@github.com:PaarXul/Quarkus-API-Microservices.git
    cd Quarkus-API-Microservices
    ```

2. Construir y ejecutar los servicios con Docker Compose:

    ```bash
    ./build-all.sh
    ```

   Este script realizará las siguientes acciones:
    - Detener y eliminar contenedores previos.
    - Compilar los servicios `task-service`, `category-service` y `api-gateway` sin ejecutar los tests.
    - Construir y levantar los servicios utilizando Docker Compose.

3. Acceder a la aplicación:

    - **API Gateway**: [http://localhost:8080](http://localhost:8080)
    - **Task Service**: [http://localhost:8081](http://localhost:8081)
    - **Category Service**: [http://localhost:8082](http://localhost:8082)

## Estructura del proyecto

- `task-service`: Código fuente del servicio de tareas.
- `category-service`: Código fuente del servicio de categorías.
- `api-gateway`: Código fuente de la puerta de enlace.
- `docker-compose.yml`: Archivo de configuración de Docker Compose.
- `build-all.sh`: Script para compilar y ejecutar los servicios.

## Notas

- Asegúrate de tener Docker y Docker Compose instalados en tu sistema.
- Los servicios se ejecutarán en los puertos 8080, 8081 y 8082 respectivamente.
- La base de datos PostgreSQL se ejecutará en el puerto 5432.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que desees realizar.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.