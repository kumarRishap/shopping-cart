# 🛒 Shopping Cart API

A Spring Boot REST API for managing an e-commerce shopping cart system.  
Includes modules for products, categories, carts, orders, users, and authentication.

## 🚀 Features
- **Product Management**: Create, update, delete, and view products.
- **Category Management**: Organize products into categories.
- **Cart & Cart Items**: Add/remove items, view cart details.
- **Orders**: Place and view orders.
- **User Management**: Register, update profiles, and manage roles.
- **Authentication**: JWT-based login and signup (optional).

## 🛠 Tech Stack
- **Backend:** Java 17, Spring Boot
- **Database:** JPA (configurable with MySQL/PostgreSQL)
- **Build Tool:** Maven
- **Security:** Spring Security with JWT (present but optional)
- **Lombok:** For cleaner DTOs and models

## 📂 Project Structure
## Project Structure

```text
src/main/java/com/springboot/shopping_cart
├── controller/
│   ├── AuthController.java
│   ├── CartController.java
│   ├── CartItemController.java
│   ├── CategoryController.java
│   ├── ImageController.java
│   ├── OrderController.java
│   ├── ProductController.java
│   └── UserController.java
├── data/
│   ├── DataInitializer.java
│   └── RoleRepository.java
├── dto/
│   ├── CartDto.java
│   ├── CartItemDto.java
│   ├── ImageDto.java
│   ├── OrderDto.java
│   ├── OrderItemDto.java
│   ├── ProductDto.java
│   └── UserDto.java
├── enums/
│   └── OrderStatus.java
├── exception/
│   ├── AlreadyExistsException.java
│   ├── GlobalExceptionHandler.java
│   ├── ProductNotFoundException.java
│   └── ResourceNotFoundException.java
├── model/
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Category.java
│   ├── Image.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Product.java
│   ├── Role.java
│   └── User.java
├── repository/
│   ├── CartItemRepository.java
│   ├── CartRepository.java
│   ├── CategoryRepository.java
│   ├── ImageRepository.java
│   ├── OrderRepository.java
│   ├── ProductRepository.java
│   └── UserRepository.java
├── request/
│   ├── CreateUserRequest.java
│   ├── LoginRequest.java
│   ├── ProductAddRequest.java
│   ├── ProductUpdateRequest.java
│   └── UpdateUserRequest.java
├── response/
│   ├── ApiResponse.java
│   └── JwtResponse.java
├── security/
│   ├── config/
│   │   └── ShopConfig.java
│   ├── jwt/
│   │   ├── AuthTokenFilter.java
│   │   ├── JwtAuthEntryPoint.java
│   │   └── JwtUtils.java
│   └── user/
│       ├── ShopUserDetails.java
│       └── ShopUserDetailsService.java
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

src/main/resources
└── application.properties
