# spring-grpc-kafka-microservices

This project demonstrates a **production-like microservices architecture** using **Spring Boot** (`WebFlux` & `gRPC`), **Kafka** for asynchronous messaging, **MongoDB** & **PostgreSQL** for persistence, all containerized via **Docker Compose**.

---

## 📌 Technologies Used

- **Java 21**
- **Spring Boot**
  - WebFlux (Reactive REST API)
  - gRPC (Reactive gRPC server & client)
  - Spring Kafka
  - Spring Data R2DBC (PostgreSQL)
  - Spring Data Reactive MongoDB
- **Apache Kafka & Zookeeper**
- **MongoDB**
- **PostgreSQL**
- **Docker & Docker Compose**

---

## 🗂️ Architecture Overview

```plaintext
[ REST API - WebFlux ]
         |
         |--> Order Service
         |     - PostgreSQL R2DBC
         |     - Kafka Producer
         |     - gRPC Client --> Inventory Service
         |
         |--> Inventory Service (gRPC)
               - MongoDB Reactive
               - Kafka Consumer
