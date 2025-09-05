# Blog Microservices Platform
A microservices-based blog platform built with Spring Boot.  
Includes:
- Security Client (authentication & JWT issuing)
- Blog (CRUD blog posts, role-based access)
- API Gateway (routing)
- Eureka Server (service discovery)


## Features
- User signup & login with JWT (Security Client)
- Role-based blog access (ADMIN can CRUD, READER can only read posts)
- Centralized routing via API Gateway
- Service discovery with Eureka

## Tech Stack
- Spring Boot (Web, Security, Data JPA)
- Spring Cloud (Eureka, Gateway)
- JWT Authentication
- PostgreSQL - Supabase

## Prerequisites
- JDK 17 
- Maven

## Setup Instructions
- git clone https://github.com/IDwomor/slightly-techie-blog.git

### Start Eureka
- cd eureka-server 
- mvn spring-boot:run
- port - 8761

### Start Security-Client
- cd security-client 
- mvn spring-boot:run
- port - 8081

### Start Api-Gateway
- cd api-gateway 
- mvn spring-boot:run
- port - 8082

### Start Blog
- cd blog-service 
- mvn spring-boot:run
- port - 8080