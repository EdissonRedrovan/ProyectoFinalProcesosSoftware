# Customer Management API 🚀

This project is a **Customer Management API** developed using **Quarkus**, designed to handle customer data operations such as creating, retrieving, updating, and deleting customers. The API is built with scalability, performance, and data integrity in mind, following best practices for RESTful services.

---

## 📋 Features

- **Customer Management**: 
  - Add new customers.
  - Retrieve all customers.
  - Update customer details (excluding unique identifiers like ID and cedula).
  - Delete customers by ID.

- **Validation**:
  - Automatic validation of cedula (Ecuadorian ID format).
  - Validation of email addresses using regular expressions.

- **Database Integration**:
  - MySQL integration for persistent storage of customer data.
  - Full CRUD support using Panache Repository.

- **Error Handling**:
  - Meaningful error messages for invalid data, duplicate entries, and system errors.

---

## 🛠️ Technologies Used

- **Quarkus**: High-performance Java framework.
- **MySQL**: Relational database for data persistence.
- **Docker**: Containerized deployment of the database and application.
- **Lombok**: Simplifies boilerplate code with annotations.
- **RESTEasy**: For building RESTful APIs.
- **Logback**: Enhanced logging and debugging support.

---

## 🔧 Prerequisites

Ensure you have the following installed:

- **Java 11+**
- **Maven 3.8+**
- **Docker**
- **Postman** or any REST client for testing

---

## 🖥️ Running the Application

### 1. Start MySQL with Docker
Run the following command to start a MySQL container:
Download before the mysql latest image docker
```bash
docker run --name mysql-customer \
  -e MYSQL_ROOT_PASSWORD=admin \
  -e MYSQL_DATABASE=db_customer \
  -e MYSQL_USER=admin \
  -e MYSQL_PASSWORD=admin \
  -v mysql_data:/var/lib/mysql \
  -p 3307:3306 \
  -d mysql:latest
