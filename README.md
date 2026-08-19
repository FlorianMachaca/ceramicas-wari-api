# 🏺 Cerámicas Wari API

API REST desarrollada con **Java, Spring Boot y Microsoft SQL Server** para gestionar los principales procesos relacionados con la producción y comercialización de productos artesanales.

El proyecto implementa una arquitectura por capas, persistencia de datos con JPA/Hibernate, validación de información, relaciones entre entidades y manejo centralizado de errores HTTP.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 4
- Spring Web
- Spring Data JPA
- Hibernate
- Jakarta Validation
- Maven
- Microsoft SQL Server
- Microsoft JDBC Driver for SQL Server
- Git
- GitHub

---

## ✨ Características principales

- API REST con operaciones CRUD.
- Arquitectura por capas.
- Persistencia mediante Spring Data JPA.
- Integración con Microsoft SQL Server.
- Validación de datos con Jakarta Validation.
- Manejo global de excepciones.
- Relaciones entre entidades.
- Validación de claves foráneas.
- Búsquedas personalizadas.
- Control de reglas de negocio.
- Respuestas HTTP apropiadas.
- Control de conflictos de integridad de datos.

---

## 🏗️ Arquitectura

El proyecto está organizado mediante una arquitectura por capas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Model
    ↓
SQL Server
```

### Controller

Expone los endpoints REST y recibe las solicitudes HTTP.

### Service

Contiene la lógica de negocio y las validaciones necesarias antes de acceder a los datos.

### Repository

Gestiona el acceso a la base de datos mediante Spring Data JPA.

### Model

Contiene las entidades JPA que representan las tablas de SQL Server.

### Exception

Centraliza el manejo de errores de toda la API.

---

## 📦 Módulos implementados

La API cuenta con los siguientes módulos:

- Productos
- Clientes
- Pedidos
- Detalles de pedido
- Artesanos
- Producción
- Proveedores
- Materias primas
- Pagos
- Exportaciones

---

# 🔗 Endpoints

## 📦 Productos

```http
GET    /api/productos
GET    /api/productos/{id}
GET    /api/productos/buscar?nombre={nombre}
POST   /api/productos
PUT    /api/productos/{id}
DELETE /api/productos/{id}
```

Permite administrar los productos artesanales y controlar información como nombre, código, precio y stock.

---

## 👤 Clientes

```http
GET    /api/clientes
GET    /api/clientes/{id}
GET    /api/clientes/buscar?nombre={nombre}
POST   /api/clientes
PUT    /api/clientes/{id}
DELETE /api/clientes/{id}
```

Gestiona los clientes nacionales e internacionales registrados en el sistema.

---

## 📝 Pedidos

```http
GET    /api/pedidos
GET    /api/pedidos/{id}
GET    /api/pedidos/cliente/{idCliente}
GET    /api/pedidos/buscar?estado={estado}
POST   /api/pedidos
PUT    /api/pedidos/{id}
DELETE /api/pedidos/{id}
```

Permite administrar pedidos y relacionarlos con sus respectivos clientes.

Estados permitidos:

```text
Pendiente
En producción
Entregado
```

---

## 🧾 Detalles de pedido

```http
GET    /api/detalles-pedido
GET    /api/detalles-pedido/{id}
GET    /api/detalles-pedido/pedido/{idPedido}
GET    /api/detalles-pedido/producto/{idProducto}
POST   /api/detalles-pedido
PUT    /api/detalles-pedido/{id}
DELETE /api/detalles-pedido/{id}
```

Relaciona los pedidos con los productos solicitados.

El subtotal es calculado automáticamente por la base de datos.

---

## 🧑‍🎨 Artesanos

```http
GET    /api/artesanos
GET    /api/artesanos/{id}
POST   /api/artesanos
PUT    /api/artesanos/{id}
DELETE /api/artesanos/{id}
```

Gestiona la información de los artesanos encargados de la producción.

---

## 🏭 Producción

```http
GET    /api/producciones
GET    /api/producciones/{id}
GET    /api/producciones/producto/{idProducto}
GET    /api/producciones/artesano/{idArtesano}
GET    /api/producciones/buscar?etapa={etapa}
POST   /api/producciones
PUT    /api/producciones/{id}
DELETE /api/producciones/{id}
```

Permite controlar el proceso de fabricación de las piezas artesanales.

Etapas permitidas:

```text
Modelado
Secado
Decoración
Cocción
Acabado
```

---

## 🚚 Proveedores

```http
GET    /api/proveedores
GET    /api/proveedores/{id}
GET    /api/proveedores/buscar?nombre={nombre}
GET    /api/proveedores/localidad/{localidad}
POST   /api/proveedores
PUT    /api/proveedores/{id}
DELETE /api/proveedores/{id}
```

Gestiona proveedores de materiales e insumos.

Localidades permitidas en la base de datos:

```text
Quinua
San Miguel
Acosvinchos
```

---

## 🧱 Materias primas

```http
GET    /api/materias-primas
GET    /api/materias-primas/{id}
GET    /api/materias-primas/buscar?nombre={nombre}
GET    /api/materias-primas/proveedor/{idProveedor}
GET    /api/materias-primas/stock-bajo
POST   /api/materias-primas
PUT    /api/materias-primas/{id}
DELETE /api/materias-primas/{id}
```

Permite controlar materiales, proveedores y niveles de inventario.

El endpoint:

```http
GET /api/materias-primas/stock-bajo
```

permite identificar materiales cuyo stock actual está por debajo del stock mínimo.

---

## 💰 Pagos

```http
GET    /api/pagos
GET    /api/pagos/{id}
GET    /api/pagos/pedido/{idPedido}
GET    /api/pagos/tipo/{tipoPago}
POST   /api/pagos
PUT    /api/pagos/{id}
DELETE /api/pagos/{id}
```

Tipos de pago permitidos:

```text
Contado
Saldo
Adelanto
```

Los montos deben ser mayores que cero.

---

## 🌎 Exportaciones

```http
GET    /api/exportaciones
GET    /api/exportaciones/{id}
GET    /api/exportaciones/pedido/{idPedido}
GET    /api/exportaciones/estado/{estadoEnvio}
GET    /api/exportaciones/buscar?courier={courier}
POST   /api/exportaciones
PUT    /api/exportaciones/{id}
DELETE /api/exportaciones/{id}
```

Estados de envío permitidos:

```text
Embalado
Enviado
Entregado
```

Cada pedido puede tener como máximo una exportación registrada.

---

# ✅ Validaciones implementadas

La API utiliza **Jakarta Validation** y validaciones de negocio.

Entre las principales validaciones se encuentran:

- Campos obligatorios.
- Longitud máxima de textos.
- Stock no negativo.
- Cantidades mayores que cero.
- Montos mayores que cero.
- Validación de estados de pedidos.
- Validación de etapas de producción.
- Validación de tipos de pago.
- Validación de estados de exportación.
- Validación de fechas.
- Validación de correo electrónico.
- Validación de DNI.
- Verificación de existencia de clientes.
- Verificación de existencia de productos.
- Verificación de existencia de proveedores.
- Verificación de existencia de artesanos.
- Verificación de existencia de pedidos.
- Control de exportaciones duplicadas por pedido.

---

# ⚠️ Manejo de errores

La API utiliza un manejador global de excepciones mediante:

```text
@RestControllerAdvice
```

Principales respuestas HTTP:

| Código | Significado |
|---|---|
| `200 OK` | Operación realizada correctamente |
| `201 Created` | Recurso creado correctamente |
| `204 No Content` | Recurso eliminado correctamente |
| `400 Bad Request` | Datos enviados incorrectamente |
| `404 Not Found` | Recurso no encontrado |
| `409 Conflict` | Conflicto con los datos existentes |
| `500 Internal Server Error` | Error inesperado del servidor |

### Ejemplo de error 400

```json
{
  "monto": "El monto debe ser mayor que 0",
  "tipoPago": "El tipo de pago debe ser Contado, Saldo o Adelanto"
}
```

### Ejemplo de error 404

```json
{
  "mensaje": "Producto con ID 100 no encontrado"
}
```

### Ejemplo de error 409

```json
{
  "mensaje": "El pedido con ID 3 ya tiene una exportación registrada"
}
```

La API también captura conflictos de integridad provenientes de la base de datos para evitar respuestas genéricas cuando una operación viola una restricción.

---

# 🗄️ Base de datos

El proyecto utiliza:

```text
Microsoft SQL Server
```

La base utilizada durante el desarrollo es:

```text
BD_CeramicasWari
```

Principales tablas:

```text
ARTESANO
CLIENTE
DETALLE_PEDIDO
EXPORTACION
MATERIA_PRIMA
PAGO
PEDIDO
PRODUCCION
PRODUCTO
PROVEEDOR
```

La base de datos contiene relaciones mediante claves foráneas y restricciones `CHECK`, `UNIQUE` y otras reglas de integridad.

> La base de datos debe existir previamente antes de ejecutar el backend.

---

# 🔗 Relaciones principales

Algunas de las relaciones utilizadas por la API son:

```text
CLIENTE
   ↓
PEDIDO
   ↓
DETALLE_PEDIDO
   ↓
PRODUCTO
```

También:

```text
PEDIDO → PAGO
PEDIDO → EXPORTACION
PRODUCTO → PRODUCCION
ARTESANO → PRODUCCION
PROVEEDOR → MATERIA_PRIMA
```

---

# ⚙️ Configuración

La configuración principal se encuentra en:

```text
src/main/resources/application.properties
```

La URL de conexión puede proporcionarse mediante la variable de entorno:

```text
DB_URL
```

Ejemplo de configuración:

```properties
spring.datasource.url=${DB_URL:jdbc:sqlserver://localhost:1433;databaseName=BD_CeramicasWari;integratedSecurity=true;encrypt=true;trustServerCertificate=true}

spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false
```

Esto permite utilizar una configuración externa sin dejar la aplicación ligada al nombre de una computadora específica.

---

# 📋 Requisitos

Para ejecutar el proyecto se recomienda tener instalado:

```text
Java 21
Microsoft SQL Server
Git
```

No es obligatorio instalar Maven globalmente porque el proyecto incluye **Maven Wrapper**.

---

# ▶️ Cómo ejecutar el proyecto

## 1. Clonar el repositorio

```bash
git clone https://github.com/FlorianMachaca/ceramicas-wari-api.git
```

---

## 2. Entrar al proyecto

```bash
cd ceramicas-wari-api
```

---

## 3. Preparar SQL Server

Debes disponer previamente de la base:

```text
BD_CeramicasWari
```

y configurar correctamente la conexión.

Si utilizas autenticación integrada de Windows con el Microsoft JDBC Driver, también debes disponer de la librería nativa de autenticación correspondiente.

---

## 4. Configurar la conexión

Puedes utilizar la configuración predeterminada o definir:

```text
DB_URL
```

con tu propia cadena de conexión.

---

## 5. Ejecutar la aplicación

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

---

## 6. Confirmar el inicio

Cuando la aplicación esté funcionando aparecerá un mensaje similar a:

```text
Tomcat started on port 8080
Started CeramicasWariApiApplication
```

---

## 7. Probar la API

La aplicación utiliza por defecto:

```text
http://localhost:8080
```

Ejemplo:

```text
http://localhost:8080/api/productos
```

---

# 📂 Estructura del proyecto

```text
ceramicas-wari-api
│
├── .mvn
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── florian
│   │   │           └── ceramicaswari
│   │   │               ├── controller
│   │   │               ├── exception
│   │   │               ├── model
│   │   │               ├── repository
│   │   │               └── service
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

# 🧠 Conceptos aplicados

Durante el desarrollo del proyecto se aplicaron conceptos de:

- Programación Orientada a Objetos.
- APIs REST.
- Arquitectura por capas.
- Spring Boot.
- Spring Web.
- Spring Data JPA.
- Hibernate.
- Entidades JPA.
- Relaciones entre tablas.
- Claves foráneas.
- Persistencia de datos.
- Validaciones.
- Manejo global de excepciones.
- Reglas de negocio.
- Códigos de estado HTTP.
- SQL Server.
- Maven.
- Git.
- GitHub.

---

# 📊 Estado del proyecto

Actualmente se encuentran implementados:

```text
Producto          ✅
Cliente           ✅
Pedido            ✅
Detalle Pedido    ✅
Artesano          ✅
Producción        ✅
Proveedor         ✅
Materia Prima     ✅
Pago              ✅
Exportación       ✅
```

Los principales CRUD y búsquedas de la API se encuentran implementados.

---

# 🎯 Objetivo del proyecto

El objetivo es aplicar conocimientos de desarrollo backend construyendo una API REST completa que permita trabajar con:

- Persistencia de datos.
- Relaciones entre entidades.
- Reglas de negocio.
- Validación de solicitudes.
- Manejo profesional de errores.
- Arquitectura organizada.
- Integración Java con SQL Server.
- Control de versiones.

---

# 👨‍💻 Autor

**Florian Machaca**

GitHub: [FlorianMachaca](https://github.com/FlorianMachaca)

---

## 📌 Repositorio

```text
https://github.com/FlorianMachaca/ceramicas-wari-api
```