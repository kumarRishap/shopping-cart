# 🛒 Shopping Cart REST API

A backend REST API for an e-commerce shopping cart system built with **Java 17 and Spring Boot**.

The application provides APIs for user authentication, product and category management, shopping cart operations, and order processing. It follows a layered backend architecture with Spring Data JPA for persistence and Spring Security with JWT for authentication and authorization.

## 🚀 Features

* User registration and authentication
* JWT-based authentication and authorization
* Product management
* Category management
* Shopping cart management
* Cart item management
* Order creation and order retrieval
* Image management
* Request validation
* Centralized exception handling
* DTO-based request and response handling
* Database persistence using Spring Data JPA and Hibernate
* REST API documentation using OpenAPI / Swagger

## 🛠 Tech Stack

| Technology        | Usage                            |
| ----------------- | -------------------------------- |
| Java 17           | Programming language             |
| Spring Boot       | Backend framework                |
| Spring MVC        | REST API development             |
| Spring Security   | Authentication and authorization |
| JWT               | Token-based authentication       |
| Spring Data JPA   | Data persistence                 |
| Hibernate         | ORM                              |
| MySQL             | Relational database              |
| Maven             | Build and dependency management  |
| Lombok            | Boilerplate reduction            |
| ModelMapper       | Object mapping                   |
| OpenAPI / Swagger | API documentation                |
| Git / GitHub      | Version control                  |

## 🏗 Architecture

The application follows a layered architecture:

```text
Client
   │
   ▼
Controller Layer
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
MySQL Database
```

### Main layers

**Controller**

Handles HTTP requests, validates input, and returns appropriate API responses.

**Service**

Contains application and business logic.

**Repository**

Handles database access using Spring Data JPA.

**DTO / Request / Response**

Separates API contracts from internal entity models.

**Security**

Handles authentication, JWT processing, and authorization.

**Exception**

Provides centralized handling of application exceptions.

## 📂 Project Structure

```text
src/main/java/com/springboot/shopping_cart

├── controller/
├── data/
├── dto/
├── enums/
├── exception/
├── model/
├── repository/
├── request/
├── response/
├── security/
├── service/
│   ├── cart/
│   ├── category/
│   ├── image/
│   ├── order/
│   ├── product/
│   └── user/
│
└── ShoppingCartApplication.java
```

## 🔐 Authentication

The application uses Spring Security with JWT-based authentication.

Typical authentication flow:

```text
Client
   │
   │ Login credentials
   ▼
Authentication API
   │
   ▼
Spring Security
   │
   ▼
JWT generated
   │
   ▼
Client
   │
   │ Bearer Token
   ▼
Protected API
```

Protected endpoints require a valid JWT token in the request:

```http
Authorization: Bearer <token>
```

## 🗄 Database

The application uses MySQL with Spring Data JPA and Hibernate.

Database configuration is provided through application configuration and should be supplied using environment-specific values rather than committed credentials.

## 📖 API Documentation

The application uses OpenAPI / Swagger for API documentation.

After starting the application, Swagger UI can be accessed through the configured OpenAPI endpoint.

## ▶️ Running the Application

### Prerequisites

* Java 17
* Maven
* MySQL

### Clone the repository

```bash
git clone https://github.com/kumarRishap/shopping-cart.git

cd shopping-cart
```

### Configure the database

Create a MySQL database and configure the required database properties in the application's environment-specific configuration.

Do not commit database passwords, JWT secrets, or other sensitive configuration to GitHub.

### Build the project

```bash
./mvnw clean package
```

On Windows:

```cmd
mvnw.cmd clean package
```

### Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```cmd
mvnw.cmd spring-boot:run
```

## 🧪 Testing

The project includes Spring Boot testing dependencies and can be tested using:

```bash
./mvnw test
```

On Windows:

```cmd
mvnw.cmd test
```

## 🔮 Planned Improvements

The project is being evolved toward a more production-oriented backend architecture.

Planned improvements include:

* Docker containerization
* Redis caching
* Kafka-based event processing
* Improved automated test coverage
* Pagination and advanced filtering
* Performance improvements
* Further architectural separation where appropriate

## 👨‍💻 Author

**Rishap Kumar**

[GitHub](https://github.com/kumarRishap)
[LinkedIn](https://linkedin.com/in/kumar-rishap)
