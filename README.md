# Banking Microservices Project

> A complete banking microservices system built with **Spring Boot**, **Spring Cloud**, **OpenFeign**, **Eureka**, **API Gateway**, and **MySQL**.

---

## 🚀 Overview

This project implements a **real-world banking microservices architecture**.  
Each microservice handles a specific domain:

| Service | Port | Purpose |
|---------|------|---------|
| Registration Service | 8081 | User signup |
| Authentication Service | 8082 | Login & JWT |
| Account Service | 8083 | Account management |
| Transfer Service | 8084 | Money transfer between accounts |
| Bill Payment Service | 8085 | Pay bills |
| Eureka Server | 8761 | Service discovery |
| API Gateway | 9090 | Unified entry point |
| Config Server | 8888 | Centralized configuration |

---

## 🧰 Features

✔ Spring Boot  
✔ Microservices architecture  
✔ Service Discovery with **Eureka**  
✔ Inter-service communication with **OpenFeign**  
✔ Centralized Config Server  
✔ API Gateway (Spring Cloud Gateway)  
✔ MySQL for persistence  
✔ Validation and exception handling

---

## 🛠 Technologies Used

* **Java 17**
* **Spring Boot**
* **Spring Cloud (Netflix Eureka, OpenFeign)**
* **MySQL**
* **REST APIs**
* **Maven**

---

## 🔧 Setup & Run

### 1. Clone the repository

```bash
git clone https://github.com/thirumalesu-pullolla/banking-microservices-project.git
cd banking-microservices-project
