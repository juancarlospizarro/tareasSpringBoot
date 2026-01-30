# CRUD MVC con Thymeleaf — RA3

## 1) Datos del alumno/a
- Nombre y apellidos: Juan Carlos Pizarro Alonso
- Curso / Grupo: 2º Desarrollo de Aplicaciones Web
- Entidad elegida (ej. Producto, Libro...): Equipo

## 2) Repositorio (fork) y gestión de versiones
- Repositorio base: https://github.com/profeInformatica101/tareasSpringBoot
- Enlace a MI fork: [[Enlace](https://github.com/juancarlospizarro/tareasSpringBoot)]
- Nº de commits realizados: 27

## 3) Arquitectura
Explica brevemente cómo has organizado:
- Controller: Clase EquipoControlador con las diferentes rutas del MVC en el paquete Controlador
- Service: Interfaz EquipoServicio con la definición de métodos y la clase EquipoServicioImpl con la implementación de esos métodos en el paquete Servicio
- Repository: Clase EquipoRepositorio en el paquete Repositorio
- Entity: Clase Equipo correspondiente a la entidad Equipo en el paquete Entidad
- Configuration: Clase IniciarDatos para la generación de datos de prueba usando Faker en el paquete Configuración

## 4) Base de datos elegida (marca una)
- [X]  H2
- [ ]  MySQL
- [ ]  PostgreSQL

## 5) Configuración de la base de datos
### 5.1 Dependencias añadidas
```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-h2console</artifactId>
</dependency>
```
(Añadida esta dependencia para que poder acceder a la consola de H2 sin problemas)

### 5.2 application.properties / application.yml
```
server.port=8081

spring.datasource.url=jdbc:h2:mem:cruddb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
### 5.3 Pasos para crear la BD (si aplica)
No es necesario crear la base de datos manualmente ya que se utiliza H2 en el proyecto.

## 6) Cómo ejecutar el proyecto
1. Requisitos (Java versión, Maven/Gradle, DB instalada si aplica): Java 21 y Maven.
2. Comando de arranque:
   - mvn spring-boot:run (Desde la subcarpeta tareaRA3, que es donde se encuentra el proyecto de Spring Boot)
3. URL de acceso:
   - http://localhost:8081/ (Acceso a la página de inicio)

## 7) Pantallas / Rutas MVC
- GET /equipos (listar)
- GET /equipos/nuevo (formulario alta)
- POST /equipos (crear)
- GET /equipos/{id}/editar (formulario editar)
- POST /equipos/{id} (actualizar)
- POST /equipos/{id}/eliminar (eliminar)
- GET /equipos/{id} (ver detalles de equipo)


## 8) Mejoras extra (opcional)
- [X]  Validaciones
- [X]  Estilos Bootstrap 
- [X]  Búsqueda 
- [ ]  Pruebas 
- [X]  Paginación
