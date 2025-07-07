FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /build

# Dependências
RUN apt-get update && \
    apt-get install -y protobuf-compiler curl && \
    curl -Lo /usr/local/bin/protoc-gen-grpc-java https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.64.0/protoc-gen-grpc-java-1.64.0-linux-x86_64.exe && \
    chmod +x /usr/local/bin/protoc-gen-grpc-java

COPY . .
# 1️⃣ INSTALA O POM PARENT
RUN mvn install -N -DskipTests

# STAGE 1: Build do proto sem repackage
RUN mvn clean install -pl common-protos -DskipTests

# STAGE 2: Build do serviço COM repackage
RUN mvn clean package spring-boot:repackage -pl inventory-service -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /build/inventory-service/target/inventory-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
EXPOSE 6565

ENTRYPOINT ["java", "-jar", "app.jar"]
