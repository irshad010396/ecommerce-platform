# Production-Ready E-Commerce Microservices Platform

A cloud-native E-Commerce platform built using Spring Boot Microservices architecture with secure JWT authentication, API Gateway, Service Discovery, Docker containerization, and Kubernetes deployment.

## Key Features

### Security

* JWT Authentication & Authorization
* Refresh Token Support
* Role-Based Access Control (RBAC)
* BCrypt Password Encryption
* Secure API Access via Spring Security

### Microservices Architecture

* User Service
* Product Service
* Order Service
* API Gateway
* Eureka Service Registry

### Cloud-Native Features

* Docker Containerization
* Kubernetes Deployment Manifests
* Service Discovery using Eureka
* Load Balancing
* Inter-Service Communication

### API & Observability

* OpenAPI / Swagger Documentation
* Centralized Logging using Logback
* Global Exception Handling
* Standardized API Responses
* API Versioning
* Spring Boot Actuator Monitoring

---

## Architecture

Client
↓
API Gateway
↓
Eureka Service Discovery
↓
User Service | Product Service | Order Service
↓
MongoDB

---

## Tech Stack

| Category         | Technology           |
| ---------------- | -------------------- |
| Language         | Java 17              |
| Framework        | Spring Boot 3        |
| Security         | Spring Security, JWT |
| Microservices    | Spring Cloud, Eureka |
| Gateway          | Spring Cloud Gateway |
| Database         | MongoDB              |
| Documentation    | Swagger/OpenAPI      |
| Build Tool       | Maven                |
| Containerization | Docker               |
| Orchestration    | Kubernetes           |

---

## Services

### User Service

* User Registration
* Login
* JWT Generation
* Refresh Token Management
* Role Management

### Product Service

* Product CRUD Operations
* Product Search
* Product Management

### Order Service

* Create Orders
* View Orders
* Order Processing

### API Gateway

* Centralized Routing
* Authentication Validation
* Request Forwarding

### Eureka Server

* Service Registration
* Service Discovery

---

## Running Locally

### Clone Repository

```bash
git clone https://github.com/irshad010396/ecommerce-platform.git
cd ecommerce-platform
```

### Build Application

```bash
mvn clean install
```

### Start Services

1. Eureka Server
2. User Service
3. Product Service
4. Order Service
5. API Gateway

### Access Applications

| Service          | URL                                                                                            |
| ---------------- | ---------------------------------------------------------------------------------------------- |
| Eureka Dashboard | http://localhost:8761                                                                          |
| API Gateway      | http://localhost:8080                                                                          |
| Swagger UI       | [http://localhost:{port}/swagger-ui/index.html](http://localhost:{port}/swagger-ui/index.html) |

---

## Docker Deployment

Build Images

```bash
docker build -t user-service .
docker build -t product-service .
docker build -t order-service .
docker build -t api-gateway .
docker build -t eureka-server .
```

Run Containers

```bash
docker-compose up -d
```

---

## Kubernetes Deployment

```bash
kubectl apply -f k8s/
```

Verify Deployments

```bash
kubectl get pods
kubectl get services
```

---

## Future Enhancements

* Apache Kafka Event-Driven Communication
* Resilience4j Circuit Breaker
* Distributed Tracing (Zipkin/OpenTelemetry)
* Prometheus & Grafana Monitoring
* CI/CD using GitHub Actions
* Spring Cloud Config Server

---

## Project Goals

This project was developed to gain hands-on experience with enterprise-grade backend development practices including microservices architecture, cloud-native deployment, security implementation, service discovery, API gateway patterns, and container orchestration.
