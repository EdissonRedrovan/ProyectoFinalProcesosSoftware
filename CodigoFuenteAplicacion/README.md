<<<<<<< HEAD
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
=======
# Introduction 
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Installation process
2.	Software dependencies
3.	Latest releases
4.	API references

# Build and Test
TODO: Describe and show how to build your code and run the tests. 

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)
>>>>>>> beff62d71f2f697ad3456e7614b96f099a2ddaf4
