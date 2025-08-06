# Technical Documentation

## Project Name: VaquerosInTech Auth API  
**Author:** Andrew Alvarez  
**Technology Stack:** Java 17+, Spring Boot, Gradle, PostgreSQL, Gradle Wrapper  
**Project Structure Type:** Gradle (with wrapper) - Spring Boot Application  

---

## Project Overview  
This project is a secure backend API for authentication and user registration for the VaquerosInTech platform. It uses:

- Spring Boot for the application framework  
- Spring Security for password hashing and CORS  
- Gradle as the build system (with wrapper)  
- PostgreSQL as the relational database  
- Gradle Wrapper scripts (`gradlew`, `gradlew.bat`) for consistent builds  

---

- Ensures all contributors use Gradle 8.14.3 to avoid version mismatches  

---

## Authentication API Details

### Endpoints


## 🔐 Login API

## 🔐 Auth API Endpoints

---  

### 🔹 POST `/api/auth/register`

**Description:**  
Creates a user with their email, name, graduation date, major,  and password. Returns a JWT token on successful registers.

#### 🔸 Request Body

```json
{
  "token": "your.jwt.token",
  "user": {
    "id": "string",
    "email": "user@example.com",
    "name": "John Doe",
    "role": "student"
  }
}


### 🔹 POST `/api/auth/login`

**Description:**  
Authenticates a user with their email and password. Returns a JWT token on successful login.

#### 🔸 Request Body

```json
{
  "email": "user@example.com",
  "password": "yourPassword123"
}
