# CRUD MVC con Thymeleaf — RA3

## 1) Datos del alumno/a
- Nombre y apellidos: Juan Carlos Pizarro Alonso
- Curso / Grupo: 2º Desarrollo de Aplicaciones Web
- Entidad elegida (ej. Producto, Libro...): Equipo

## 2) Repositorio (fork) y gestión de versiones
- Repositorio base: https://github.com/profeInformatica101/tareasSpringBoot
- Enlace a MI fork: [[Enlace](https://github.com/juancarlospizarro/tareasSpringBoot)]
- Nº de commits realizados: 31
- Commit de entrega: Commit siguiente al d117b91
- Rama de trabajo: main

## 3) Arquitectura
Explica brevemente cómo has organizado:
- Controller: Clase EquipoControlador, LoginControlador, InicioControlador, UsuarioAdviceController, UsuarioControlador y GlobalExceptionHandler con las diferentes rutas del MVC en el paquete Controlador
- Service: Interfaz EquipoServicio y UsuarioServicio con la definición de métodos y la clase EquipoServicioImpl y UsuarioServicioImpl con la implementación de esos métodos además de UserDetailsServiceImpl en el paquete Servicio
- Repository: Clase EquipoRepositorio y UsuarioRepositorio en el paquete Repositorio
- Entity: Clase Equipo, Usuario y Rol correspondiente a la entidad Equipo, la entidad Usuario y el enumerado Rol respectivamente en el paquete Entidad
- Configuration: Clase IniciarDatos para la generación de datos de prueba usando Faker y clase Seguridad para gestionar el acceso a la aplicación web en el paquete Configuración

## 4) Base de datos elegida (marca una)
- [ ]  H2
- [X]  MySQL
- [ ]  PostgreSQL

## 5) Configuración de la base de datos
### 5.1 Dependencias añadidas
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

### 5.2 application.properties / application.yml
```
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/tarea_ra3?useUnicode=true&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
```
### 5.3 Pasos para crear la BD (si aplica)
Para poder trabajar con la base de datos, es necesario antes crearla usando la sentencia siguiente: CREATE DATABASE tarea_ra3

## 6) Cómo ejecutar el proyecto
1. Requisitos (Java versión, Maven/Gradle, DB instalada si aplica): Java 21 y Maven.
2. Comando de arranque:
   - mvn spring-boot:run (Desde la subcarpeta tareaRA3, que es donde se encuentra el proyecto de Spring Boot)
3. URL de acceso:
   - http://localhost:8081/ (Acceso a la página de invitado. A partir de ella se puede iniciar sesión y acceder al contenido)

## 7) Pantallas / Rutas MVC
### Generales
- GET / (invitado)
- GET /inicio (inicio de usuario)
- GET /login (inicio de sesión)

### Equipos
- GET /equipos (listar)
- GET /equipos/nuevo (formulario alta)
- POST /equipos (crear)
- GET /equipos/{id} (ver detalles de equipo)
- GET /equipos/{id}/editar (formulario editar)
- POST /equipos/{id} (actualizar)
- POST /equipos/{id}/eliminar (eliminar)

### Usuarios
- GET /usuarios (listar usuarios)
- GET /usuarios/nuevo (formulario alta usuario)
- POST /usuarios (crear usuario)
- GET /usuarios/{id}/editar (formulario editar usuario)
- POST /usuarios/{id} (actualizar usuario)
- POST /usuarios/{id}/eliminar (eliminar usuario)

A las diferentes vistas podrán acceder en función del rol del usuario. En caso de ser un usuario normal podrá acceder a todo menos a crear, editar y eliminar equipos. En caso de ser un usuario manager podrá acceder a todo menos a eliminar equipos y en caso de ser admin podrá acceder a todas las rutas.

Lo mismo ocurre a la hora de tratar con los usuarios registrados. Solo los usuarios con rol de manager y admin podrán acceder a la lista de usuarios. El usuario de tipo manager podrá editar y crear usuarios con rol de usuario y el admin podrá crear, editar y eliminar todo tipo de usuario.

## 8) Mejoras extra (opcional)
- [X]  Validaciones
- [X]  Estilos Bootstrap 
- [X]  Búsqueda 
- [ ]  Pruebas 
- [X]  Paginación
