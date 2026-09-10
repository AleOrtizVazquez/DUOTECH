# DúoTech Backend

Backend base reutilizable para la plataforma DúoTech.

## Stack
- Java 21
- Spring Boot 4.1.1
- Spring Data JPA
- PostgreSQL
- Spring Security
- Maven

## PostgreSQL
Crear la base:

```sql
CREATE DATABASE duotech;
```

Configura usuario/contraseña en:
`src/main/resources/application.properties`

## Ejecutar

```bash
mvnw.cmd spring-boot:run
```

o:

```bash
mvn spring-boot:run
```

API:
- GET /api/catalog
- GET /api/catalog/{id}
- GET /api/announcements
- POST /api/auth/login
- POST /api/auth/register
- POST /api/auth/recover

NOTA:
La autenticación está preparada como punto de partida/demo. Para producción se debe implementar entidad de usuario, BCrypt, JWT/refresh token, roles y autorización real.
