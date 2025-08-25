# 🛒 Shopping Cart API

A RESTful API for managing a shopping cart system. Built with **Java Spring Boot**, it supports product listing, cart management, and order processing.

## 🚀 Features
- **Product Management**
  - Add, update, delete, and retrieve products
- **Cart Management**
  - Add products to cart
  - Remove products from cart
  - View cart items with total price
- **Order Processing**
  - Place an order
  - View order details
- **User Support**
  - Multiple user carts
  - Authentication and authorization (JWT-based, if enabled)

## 🛠 Tech Stack
- **Backend:** Spring Boot (Java)
- **Database:** PostgreSQL / MySQL (configurable)
- **Build Tool:** Maven / Gradle
- **Security:** Spring Security with JWT (optional)
- **Testing:** JUnit, Mockito

## 📂 Project Structure
## Project Structure

```text
src/main/java/com/springboot/shopping_cart
├── controller/
|   ├── AuthController.java
│   ├── CartController.java
│   ├── CartItemController.java
│   ├── CategoryController.java
│   ├── ImageController.java
│   ├── OrderController.java
│   ├── ProductController.java
│   └── UserController.java
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
│   │   ├── CartItemService.java
│   │   ├── CartService.java
│   │   ├── ICartItemService.java
│   │   └── ICartService.java
│   ├── category/
│   │   ├── CategoryService.java
│   │   └── ICategoryService.java
│   ├── image/
│   │   ├── IImageService.java
│   │   └── ImageService.java
│   ├── order/
│   │   ├── IOrderService.java
│   │   └── OrderService.java
│   ├── product/
│   │   ├── IProductService.java
│   │   └── ProductService.java
│   └── user/
│       ├── IUserService.java
│       └── UserService.java
└── ShoppingCartApplication.java
