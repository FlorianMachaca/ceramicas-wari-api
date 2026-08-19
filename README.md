# 🏺 Cerámicas Wari API

API REST desarrollada con **Java, Spring Boot y SQL Server** para gestionar los principales procesos relacionados con la producción y comercialización de productos artesanales.

El proyecto implementa una arquitectura por capas, persistencia con JPA/Hibernate, validación de datos, relaciones entre entidades y manejo centralizado de errores HTTP.

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

## 🏗️ Arquitectura

El proyecto utiliza una arquitectura por capas:

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