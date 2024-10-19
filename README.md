# E-Commerce Application
## Overview

This is a sample e-commerce application developed using Java Spring Boot. The application demonstrates various features including CRUD operations, API documentation with Swagger, and logging using Logback.

## Features

1. **CRUD Operations**: Basic Create, Read, Update, and Delete operations for the services.
2. **Swagger**: API documentation and testing.
3. **Logging**: Logging implementation using Logback.

## Technologies Used

- **Java Spring Boot 3.3.1**
- **Spring Data mongodb**
- **Swagger (springdoc-openapi-starter-webmvc-ui)**
- **Logback(with slf4j-api)**
- **MongoDB**

## Setup and Installation

### Prerequisites

- Java 17 or higher
- Maven
- MongoDB

### Steps

1. Clone the Repository:
   ```sh
   git clone https://github.com/your-username/ecommerce-application.git
   cd ecommerce-application
2. Configure the Database:
Create a MongoDB database named product,orders and user.
Update the application.properties file in the src/main/resources directory with your database configurations.

3. Build the Project:
   mvn clean install
4.mvn spring-boot:run
   
   
API Documentation
The application uses Swagger for API documentation. Once the application is running, you can access the Swagger UI at:
http://localhost:{portNo}/swagger-ui/index.html
   
