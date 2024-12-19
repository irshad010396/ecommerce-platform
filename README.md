# E-Commerce Application
## Overview

This is a sample e-commerce application developed using Java Spring Boot. The application demonstrates various features including CRUD operations, API documentation with Swagger, logging using Logback, containerization with Docker and orchestration with Kubernetes.

## Features

1. **CRUD Operations**: Basic Create, Read, Update, and Delete operations for the services.
2. **Swagger**: API documentation and testing.
3. **Logging**: Logging implementation using Logback.
4. **Containerization** : Dockerfiles for containerized deployment.
5. **Kubernetes (K8s)**: Kubernetes manifests file for deployment using kubernetes.

## Technologies Used

- **Java Spring Boot 3.3.1**
- **Spring Data mongodb**
- **Swagger (springdoc-openapi-starter-webmvc-ui)**
- **Logback(with slf4j-api)**
- **MongoDB**
- **Docker**
- **Kubernetes (K8s)**

## Setup and Installation

### Prerequisites

- Java 17 or higher
- Maven
- MongoDB
- Docker
- Kubernetes

### Steps to Run the Application

1. Clone the Repository:
   ```sh
   git clone https://github.com/your-username/ecommerce-application.git
   cd ecommerce-application
2. Configure the Database:
      Create a MongoDB database named product,orders and user.
      Update the application.properties file in the src/main/resources directory with your database configurations.

3. Build and Run the Project Locally (Without Docker) :
     mvn clean install
     mvn spring-boot:run
API Documentation
The application uses Swagger for API documentation. Once the application is running, you can access the Swagger UI at:
http://localhost:{portNo}/swagger-ui/index.html

4.Run with Docker :
  Each service contains its own Dockerfile. To build and run containers:
  1.Build Docker Images:
    docker build -t order-service ./order-service
    docker build -t product-service ./product-service
    docker build -t user-service ./user-service
    docker build -t api-gateway ./api-gateway
  2.Run Docker Containers:
    docker run order-service
    docker run product-service
    docker run user-service
    docker run api-gateway

5.Run with Kubernetes
  1.Start Kubernetes Cluster:
     minikube start
  2.Apply Kubernetes Manifests:  
     kubectl apply -f k8s/order-service-deployment.yml
     kubectl apply -f k8s/product-service-deployment.yml
     kubectl apply -f k8s/user-service-deployment.yml
     kubectl apply -f k8s/api-gateway-deployment.yml



  

 
   
