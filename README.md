# Denuncias Ecuador API

API REST para la gestión de denuncias ciudadanas en Ecuador. Sistema que permite a los usuarios registrarse, autenticarse y reportar incidentes relacionados con aseo, ornato, tránsito vial y delitos.

## Descripción

Denuncias Ecuador es una plataforma backend desarrollada con Spring Boot que facilita la creación y gestión de denuncias ciudadanas. El sistema permite el registro de usuarios con sus credenciales, la creación de denuncias públicas o privadas, y la consulta de reportes por usuario o identificador.

## Tecnologías

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA** - Persistencia de datos
- **Spring Security** - Seguridad y autenticación
- **Spring Validation** - Validación de datos
- **MariaDB 12** - Base de datos
- **Maven** - Gestión de dependencias
- **Docker & Docker Compose** - Containerización

## Características

- Registro de usuarios con validación de datos
- Gestión de credenciales de acceso
- Creación de denuncias con información detallada
- Consulta de denuncias por ID o por usuario
- Soporte para denuncias públicas y privadas
- Tipos de denuncia: Aseo, Ornato, Tránsito Vial, Delito
- Paginación en consultas de denuncias
- Manejo de excepciones personalizado
- Encriptación de contraseñas

## Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas organizada por características (feature-based):

```
src/main/java/ec/com/denunciasecuador/
├── DenunciasecuadorApplication.java
├── common/                    # Utilidades comunes
│   ├── constant/
│   ├── exception/            # Excepciones personalizadas
│   └── util/                 # Utilidades (PasswordEncryptor)
├── config/                   # Configuraciones
└── feature/                  # Módulos funcionales
    ├── denuncia/            # Gestión de denuncias
    │   ├── controller/
    │   ├── dto/
    │   ├── model/
    │   ├── repository/
    │   └── service/
    ├── registro/            # Registro de usuarios
    │   ├── controller/
    │   ├── dto/
    │   └── service/
    ├── security/            # Seguridad y autenticación
    └── usuario/             # Gestión de usuarios
        ├── controller/
        ├── dto/
        ├── model/
        ├── repository/
        └── service/
```

## Requisitos Previos

- Java 21 o superior
- Maven 3.9.x
- Docker y Docker Compose (para ejecución con contenedores)
- MariaDB 12 (si se ejecuta sin Docker)

## Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone <url-repositorio>
cd denunciasecuador-api
```

### 2. Configurar variables de entorno

Crear un archivo `.env` en la raíz del proyecto basándose en `.env.example`:

```env
# Database connection for Spring Boot App
DB_URL=jdbc:mariadb://localhost:3306/denunciasecuador_db
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña

# Database creation for MariaDB Container
DB_DATABASE=denunciasecuador_db
DB_ROOT_PASSWORD=contraseña_root
```

### 3. Ejecutar con Docker Compose (Recomendado)

```bash
docker-compose up
```

Esto iniciará:
- La aplicación Spring Boot en el puerto 8080
- MariaDB en el puerto 3306

### 4. Ejecutar localmente (sin Docker)

Asegúrate de tener MariaDB instalado y configurado.

```bash
# Compilar el proyecto
./mvnw clean install

# Ejecutar la aplicación
./mvnw spring-boot:run
```

## API Endpoints

### Registro

#### Crear nuevo registro
```http
POST /registro/nuevo_registro
Content-Type: application/json

{
  "firstName": "Juan",
  "middleName": "Carlos",
  "surnames": "Pérez López",
  "identityNumber": "1234567890",
  "username": "tuNombreDeUsuario",
  "password": "contraseña_segura"
}
```

### Usuarios

#### Obtener usuario por número de identidad
```http
GET /usuarios/getByCI/{numeroIdentidad}
```

#### Obtener usuario por ID
```http
GET /usuarios/getById/{id}
```

#### Registrarse
```http
POST /registrarse/guardar_registro
Content-Type: application/json

{
  "firstName": "María",
  "middleName": "Isabel",
  "surnames": "González Ruiz",
  "identityNumber": "0987654321",
  "username": "tuNombreDeUsuario",
  "password": "contraseña_segura"
}
```

### Denuncias

#### Obtener denuncia por ID
```http
GET /denuncias/getById/{id}
```

#### Obtener denuncias por número de identidad del usuario
```http
GET /denuncias/getByIdentityNumberUsuario/{identityNumber}?page=0&size=10
```

#### Crear nueva denuncia
```http
POST /denuncias/guardar_denuncia
Content-Type: application/json

{
  "title": "Basura acumulada en esquina",
  "description": "Hay basura acumulada en la esquina de...",
  "eventTimestamp": "2025-10-20T14:30:00",
  "cityProvince": "Quito, Pichincha",
  "isPrivate": false,
  "reportType": "ASEO",
  "usuarioId": 1
}
```

#### Tipos de denuncia disponibles:
- `ASEO` - Problemas de limpieza
- `ORNATO` - Problemas de ornamentación pública
- `TRANSITO_VIAL` - Infracciones de tránsito
- `DELITO` - Reportes de delitos

## Modelo de Datos

### Usuario
- `id`: Identificador único
- `firstName`: Primer nombre
- `middleName`: Segundo nombre (opcional)
- `surnames`: Apellidos
- `identityNumber`: Número de cédula (único)
- `credential`: Credenciales de acceso

### Denuncia
- `id`: Identificador único
- `title`: Título de la denuncia
- `description`: Descripción detallada
- `eventTimestamp`: Fecha y hora del evento
- `cityProvince`: Ciudad y provincia
- `isPrivate`: Indica si la denuncia es privada
- `reportType`: Tipo de denuncia (ASEO, ORNATO, TRANSITO_VIAL, DELITO)
- `usuario`: Usuario que realiza la denuncia

## Desarrollo

### Ejecutar en modo desarrollo

El proyecto incluye Spring Boot DevTools para hot-reload durante el desarrollo:

```bash
./mvnw spring-boot:run
```

### Ejecutar tests

```bash
./mvnw test
```

### Compilar para producción

```bash
./mvnw clean package
```

El archivo JAR se generará en `target/denunciasecuador-1.0.0-SNAPSHOT.jar`

## Configuración de Base de Datos

La aplicación está configurada para usar MariaDB con las siguientes características:

- **DDL auto**: `update` - Actualiza automáticamente el esquema
- **Show SQL**: `true` - Muestra las consultas SQL en logs
- **Dialect**: MariaDB

## Docker

### Dockerfile

La imagen Docker utiliza:
- Base: `maven:3-eclipse-temurin-21-noble`
- Puerto expuesto: 8080
- Hot-reload habilitado con spring-boot-devtools

### Volúmenes

El `docker-compose.yaml` configura volúmenes para:
- Persistencia de datos de MariaDB
- Hot-reload del código fuente
- Caché de compilación Maven

## Seguridad

- Implementa Spring Security
- Las contraseñas se encriptan mediante `PasswordEncryptor`
- Validación de datos en todos los endpoints con Bean Validation
- Manejo de excepciones personalizado para entidades no encontradas

## Manejo de Errores

El sistema incluye excepciones personalizadas para:
- `UsuarioNotFoundException` - Usuario no encontrado
- `DenunciaNotFoundException` - Denuncia no encontrada
- `CredencialNotFoundException` - Credencial no encontrada

## Variables de Entorno

| Variable | Descripción | Ejemplo |
|----------|-------------|---------|
| `DB_URL` | URL de conexión a MariaDB | `jdbc:mariadb://mariadb:3306/denunciasecuador_db` |
| `DB_USERNAME` | Usuario de base de datos | `denuncias_user` |
| `DB_PASSWORD` | Contraseña de base de datos | `password123` |
| `DB_DATABASE` | Nombre de la base de datos | `denunciasecuador_db` |
| `DB_ROOT_PASSWORD` | Contraseña root de MariaDB | `rootpassword` |

## Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

## Autor

**Robin Lugo**

## Licencia

Este proyecto está bajo desarrollo. Consultar información de licencia con el autor.

## Versión

**1.0.0-SNAPSHOT**

## Soporte

Para reportar problemas o solicitar nuevas funcionalidades, por favor crear un issue en el repositorio del proyecto.
