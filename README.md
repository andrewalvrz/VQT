## 🧱 Component Diagram

### Basic Layered Architecture

```mermaid
graph TD
    ClientApp[Client Application] --> RestAPI[REST API Layer]
    RestAPI --> Controllers[Controllers Layer]
    Controllers --> Services[Service Layer]
    Services --> Repos[Repository Layer]
    Repos --> DB[Database]
```

---

### Detailed Component Diagram

```mermaid
graph TD
    ClientApp[Client Application] --> API[REST API Layer<br/>- AuthController<br/>- UserInfoCtrl<br/>- HomeController]
    API --> Services[Service Layer<br/>- UserService]
    Services --> Repo[Repository Layer<br/>- UserRepository]
    Repo --> DB[Database<br/>- PostgreSQL]
```

---

## 🔁 Sequence Diagram: User Registration

```mermaid
sequenceDiagram
    participant C as Client
    participant AC as AuthController
    participant US as UserService
    participant UR as UserRepository
    participant DB as Database

    C->>AC: POST /register
    AC->>US: register()
    US->>UR: findByEmail()
    UR->>DB: SELECT
    DB-->>UR: result
    UR-->>US: user
    US->>UR: generateUserId(), encode password
    US->>UR: save(user)
    UR->>DB: INSERT
    DB-->>UR: insert result
    UR-->>US: saved user
    US-->>AC: success
    AC-->>C: response
```

---

## 🔐 Sequence Diagram: User Login

```mermaid
sequenceDiagram
    participant C as Client
    participant AC as AuthController
    participant US as UserService
    participant UR as UserRepository
    participant DB as Database

    C->>AC: POST /login
    AC->>US: login()
    US->>UR: findByEmail()
    UR->>DB: SELECT
    DB-->>UR: result
    UR-->>US: user
    US->>US: matches(password)
    US-->>AC: success
    AC-->>C: token or error
```

---

## 🧬 Entity-Relationship Diagram

```mermaid
erDiagram
    USER {
        string id PK
        string userId "Unique"
        string email "Unique"
        string password
        string name
        string major
        string graduationDate
        string role
    }
```

---

## 🌐 API Endpoints Map

```mermaid
flowchart TD
    A[AuthAPI] --> B[Authentication]
    B --> B1[POST /api/auth/register]
    B --> B2[POST /api/auth/login]
    A --> C[User Management]
    C --> C1[GET /api/auth/userinfo]
    C --> C2[GET /api/auth/userinfo/{id}]
    C --> C3[POST /api/auth/userinfo]
    C --> C4[DELETE /api/auth/userinfo/{id}]
    C --> C5[POST /api/auth/userinfo/{id}]
```

---
