### Construcción del Proyecto

1. **Construir el proyecto usando Maven:**

   ```sh
   mvn clean install

2. **Construir la imagen de Docker:**

   ```sh
   sudo docker build -t football-service-image .

3. **Ejecutar el contenedor de Docker **

   ```sh
   sudo docker run -p 8080:8080 --name football-service-container football-service-image .


### Acceso a Swagger UI
Una vez que el contenedor esté en ejecución, puedes acceder a la documentación de la API en Swagger UI en el siguiente enlace:

[Swagger UI](http://localhost:8080/swagger-ui/index.html#/)

#### es necesario autenticarse dentro de swager para poder utilizar el resto de endpoints.