# SmartLogix Pedidos MS

Microservicio de gestión de pedidos para SmartLogix. Permite crear, consultar, actualizar y eliminar pedidos de clientes dentro de la arquitectura basada en microservicios del proyecto.

## Tecnologías
- Java 17
- Spring Boot 3.2.5
- Maven
- MySQL
- H2 Database (perfil dev)

## Requisitos
- Java 17 o superior
- Maven o Maven Wrapper
- MySQL en ejecución si se usa el perfil `mysql`
- Base de datos configurada para el perfil `mysql`

## Configuración
Este microservicio se ejecuta en el puerto `8082`.

Perfiles disponibles:
- `dev`: usa H2 en memoria para desarrollo rápido.
- `mysql`: usa MySQL local.

Base de datos utilizada en MySQL:
- Nombre: `smartlogix_pedidos`

## Instalación y ejecución

### Con perfil de desarrollo (H2)
```bash
java -jar target/pedidos-ms-1.0.0.jar --spring.profiles.active=dev
```

### Con perfil MySQL
```bash
java -jar target/pedidos-ms-1.0.0.jar --spring.profiles.active=mysql
```

### Con Maven Wrapper en Windows PowerShell
```powershell
# Perfil dev (H2)
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev"

# Perfil mysql
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

### Con Maven Wrapper en Bash / Linux / macOS
```bash
# Perfil dev (H2)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Perfil mysql
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/pedidos | Listar pedidos |
| GET | /api/pedidos/{id} | Obtener pedido por ID |
| POST | /api/pedidos | Crear pedido |
| PUT | /api/pedidos/{id} | Actualizar pedido |
| DELETE | /api/pedidos/{id} | Eliminar pedido |

## Accesos útiles

- Swagger UI: `http://localhost:8082/swagger-ui.html`
- API Docs (JSON): `http://localhost:8082/api-docs`
- H2 Console *(solo perfil dev)*: `http://localhost:8082/h2-console`

### Datos de acceso H2
- JDBC URL: `jdbc:h2:mem:smartlogix_pedidos`
- Usuario: `sa`
- Contraseña: *(vacía)*

## Prueba rápida
1. Ejecutar el microservicio con el perfil `dev`.
2. Abrir Swagger UI en `http://localhost:8082/swagger-ui.html`.
3. Probar los endpoints disponibles.
4. Para persistencia real, cambiar al perfil `mysql` y tener MySQL local en ejecución.

## Estructura de perfiles

| Archivo | Perfil | Base de datos |
|--------|--------|--------------|
| `application.properties` | (base) | Puerto, Swagger, nombre de la app |
| `application-dev.properties` | `dev` | H2 en memoria |
| `application-mysql.properties` | `mysql` | MySQL local (`smartlogix_pedidos`) |

## Autor
Proyecto desarrollado para la asignatura **Desarrollo Fullstack III**.
