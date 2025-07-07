# Spring-gRPC-Kafka-Microservices

This project demonstrates a **production-like microservices architecture** using **Spring Boot** (`WebFlux`, `gRPC`), **Kafka** for asynchronous messaging, **MongoDB** & **PostgreSQL** for persistence, all containerized with **Docker Compose**.

It showcases **Reactive Programming**, **Multithreading with Virtual Threads**, **RxJava**, **R2DBC**, and modern integration patterns.

---

## 📌 Technologies Used

- **Java 21**
  - Virtual Threads (Project Loom preview)
- **Spring Boot**
  - **WebFlux** (Reactive REST API)
  - **gRPC** (Reactive gRPC Server & Client)
  - **Spring Kafka** (Producer & Consumer)
  - **Spring Data R2DBC** (Reactive PostgreSQL)
  - **Spring Data Reactive MongoDB**
- **RxJava 3**
- **Apache Kafka & Zookeeper**
- **PostgreSQL** (R2DBC driver)
- **MongoDB** (Reactive Streams driver)
- **Docker & Docker Compose**

---

## 🗂️ Architecture Overview

```plaintext
[ REST API - Spring WebFlux ]
         |
         |--> Order Service
         |     - Reactive Streams (Mono/Flux)
         |     - RxJava 3 for async logic
         |     - Virtual Threads for blocking ops
         |     - PostgreSQL with R2DBC
         |     - Kafka Producer (event publishing)
         |     - gRPC Client --> Inventory Service
         |
         |--> Inventory Service (gRPC)
               - gRPC Server
               - MongoDB Reactive
               - Kafka Consumer (order events)
