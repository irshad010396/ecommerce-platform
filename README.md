# E-Commerce Application
## Overview

This is a sample e-commerce application developed using Java Spring Boot. The application demonstrates various features including CRUD operations, API documentation with Swagger, logging using Logback, inter-microservice communication, service discovery with Eureka, API routing through API Gateway,containerization with Docker and orchestration with Kubernetes.

## Features

1. **CRUD Operations**: Basic Create, Read, Update, and Delete operations for the services.
2. **Service Discovery**: Spring Cloud Eureka Server for dynamic service registration and discovery.
3. **API Gateway**: Routing and API management using Spring Cloud Gateway
4. **Swagger**: API documentation and testing.
5. **Logging**: Logging implementation using Logback.
6. **Containerization** : Dockerfiles for containerized deployment.
7. **Kubernetes (K8s)**: Kubernetes manifests file for deployment using kubernetes.

## Technologies Used

- **Java Spring Boot 3.3.1**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud Gateway**
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
   Each service can be accessed individually via their ports.
     Eureka Server: http://localhost:8761
     API Gateway: http://localhost:8080  
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
    docker build -t ecom-eureka-server ./ecom-eureka-server
    
  2.Run Docker Containers:
    docker run order-service
    docker run product-service
    docker run user-service
    docker run api-gateway
    docker run ecom-eureka-server

5.Run with Kubernetes
  1.Start Kubernetes Cluster:
     minikube start
  2.Apply Kubernetes Manifests:  
     kubectl apply -f k8s/order-service.yaml
     kubectl apply -f k8s/product-service.yaml
     kubectl apply -f k8s/user-service.yaml
     kubectl apply -f k8s/api-gateway.yaml
     kubectl apply -f k8s/ecom-eureka-server.yaml
  3.Access Services:
     Eureka Server: http://<minikube-ip>:<eureka-port>
     API Gateway: http://<minikube-ip>:<gateway-port>   
