# SmartLogix Pedidos MS

Microservicio de gestión de pedidos para SmartLogix. Permite crear, consultar, actualizar y eliminar pedidos de clientes dentro de la arquitectura basada en microservicios del proyecto.

## Tecnologías
- Java 17
- Spring Boot
- Maven
- MySQL

## Requisitos
- Java 17 o superior
- Maven o Maven Wrapper
- MySQL en ejecución
- Base de datos configurada para el proyecto

## Configuración
Este microservicio se ejecuta en el puerto `8082`.

Base de datos utilizada:
- Nombre: `smartlogix_pedidos`

## Instalación y ejecución

```bash
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/pedidos | Listar pedidos |
| GET | /api/pedidos/{id} | Obtener pedido por ID |
| POST | /api/pedidos | Crear pedido |
| PUT | /api/pedidos/{id} | Actualizar pedido |
| DELETE | /api/pedidos/{id} | Eliminar pedido |

## Prueba rápida
1. Levantar la base de datos MySQL.
2. Ejecutar el microservicio.
3. Probar los endpoints con Postman, Swagger o a través del BFF.
4. Verificar que responde en `http://localhost:8082`.

## Autor
Proyecto desarrollado para la asignatura **Desarrollo Fullstack III**.
