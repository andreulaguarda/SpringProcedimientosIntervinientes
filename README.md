# Tarea2 Feign

Este proyecto es una aplicación web de gestión de procedimientos e intervinientes. Permite a los usuarios crear, leer,
actualizar y eliminar procedimientos, así como los intervinientes asociados a cada procedimiento.

Consta de dos microservicios independientes, uno para procedimientos, cada uno con su propia base de datos en memoria H2.

Al tratarse de una relación de uno a muchos, un procedimiento puede tener varios intervinientes, pero un interviniente
solo puede estar asociado a un procedimiento. Por este motivo, cada interviniente contiene una referencia a la id 
del procedimiento al que pertenece.

El microservicio de procedimientos utiliza un cliente Feign para comunicarse con el microservicio de intervinientes y
obtener, crear, actualizar o eliminar los intervinientes asociados a un procedimiento.

## Cómo ejecutar la aplicación

Para ejecutar el proyecto, necesitas tener instalado Java y Maven. Luego, ejecutar cada microservicio mediante el
siguiente comando en la raíz de cada microservicio: `/msvc-procedimientos` y `/msvc-intervinientes`.

```bash
mvn spring-boot:run
```

## Cómo probar la aplicación
Con los dos microservicios en ejecución, puedes probar los endpoints de la API con Postman o Swagger, aunque se
recomienda usar Postman, ya que importando la colección se obtienen las consultas preparadas.

### Postman:
Puedes importar la colección de peticiones se encuentra en la carpeta `postman` del proyecto. Simplemente arrastrando a 
Postman el archivo JSON `Tarea2  Feign.postman_collection`.

### Swagger:
Para probarlo con Swagger, puedes acceder a la URL de cada microservicio en un navegador:

#### Procedimientos:
http://localhost:8001/swagger-ui/index.html.
#### Intervinientes:
http://localhost:8002/swagger-ui/index.html.


## Pruebas unitarias

Se han realizado pruebas unitarias para el servicio `ProcedimientoService` y el controlador `ProcedimientoController`. 

Se pueden ejecutar las pruebas en la carpeta `src/test/java/com/andreu/procedimientos`.

También se puede consultar el informe de cobertura de las pruebas unitarias abriendo en un navegador el archivo

[index.html](./htmlReport/index.html) ubicado en la carpeta `htmlReport`."

- `ProcedimientoServiceImplTest`: Pruebas unitarias para el servicio `ProcedimientoService`.
- `ProcedimientoControllerTest`: Pruebas unitarias para el controlador `ProcedimientoController`.

## Depuración con SonarLint
Se ha utilizado SonarLint para revisar el código y asegurar la calidad del mismo. 

## Tecnologías utilizadas

- ### Java:
Para la implementación de la lógica de negocio.
- ### Spring Boot: 
Para la creación de la aplicación web.
- ### Maven: 
Para la gestión de dependencias.
- ### Open Feign:
Para la comunicación entre microservicios.
- ### Lombook:
Para la generación de getters, setters, constructores, etc.
- ### H2 Database:
Para la base de datos en memoria.
- ### ModelMapper: 
Para mapear entidades a DTOs y viceversa.
- ### Junit:
Para la realización de pruebas unitarias.
- ### Mockito:
Para la realización de pruebas unitarias.
- ### SonarLint:
Para la revisión de código.
- ### Postman:
Para la realización de pruebas de integración.
- ### Swagger:
Para la documentación de la API.
- ### Git y GitLab:
Para el control de versiones.

