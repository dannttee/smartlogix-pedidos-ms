# SmartLogix Pedidos MS

Microservicio de gestión de pedidos para SmartLogix. Administra la creación y seguimiento de pedidos de clientes.

## Tecnologías
- Java 17
- Spring Boot
- Maven
- MySQL/MariaDB

## Requisitos
- Java 17+
- Maven
- Base de datos MySQL corriendo

## Instalación y ejecución

```bash
.\mvnw spring-boot:run
```

## Endpoints disponibles
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/pedidos | Listar pedidos |
| GET | /api/pedidos/{id} | Obtener pedido |
| POST | /api/pedidos | Crear pedido |
| PUT | /api/pedidos/{id} | Actualizar pedido |
| DELETE | /api/pedidos/{id} | Eliminar pedido |
