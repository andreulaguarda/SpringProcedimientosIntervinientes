# Tarea2 Feign - Procedimientos e Intervinientes

Este proyecto es una aplicación web de gestión de procedimientos e intervinientes. Permite a los usuarios crear, leer,
actualizar y eliminar procedimientos, así como los intervinientes asociados a cada procedimiento.

Consta de dos microservicios independientes, uno para procedimientos, cada uno con su propia base de datos en memoria H2.

Al tratarse de una relación de uno a muchos, un procedimiento puede tener varios intervinientes, pero un interviniente
solo puede estar asociado a un procedimiento. Por este motivo, cada interviniente contiene una referencia a la id 
del procedimiento al que pertenece, utilizada para relacionar los intervinientes con los procedimientos, listar
los intervinientes asociados a un procedimiento, etc.

La aplicación está pensada para que la creación, actualización o eliminación de intervinientes asociados a un 
procedimiento se realice a través del microservicio de procedimientos, que a su vez se comunica con el CRUD del 
microservicio de intervinientes mediante Feign.

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

Se han realizado pruebas unitarias para todas las clases de la aplicación. 

### Ejecución de las pruebas unitarias del microservicio de procedimientos

Se pueden ejecutar las pruebas en la carpeta `/mscv-procedimientos/src/test/`.

Todos los tests superados con éxito con una cobertura del 100% en clases y métodos, y un 87% en líneas.

### Ejecución de las pruebas unitarias del microservicio de intervinientes

Se pueden ejecutar las pruebas en la carpeta `/mscv-intervinientes/src/test/`.

Todos los tests superados con éxito con una cobertura del 100% en clases y métodos, y un 94% en líneas.

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

