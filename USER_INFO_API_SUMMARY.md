# User Info API - Implementation and Response Format

## Overview

The User Info API provides endpoints to manage user information in the system. It allows clients to:

1. Retrieve all users
2. Retrieve a specific user by ID
3. Create a new user
4. Delete a user

## API Endpoints

### GET /api/auth/userinfo
Returns a list of all users in the system.

### GET /api/auth/userinfo/{id}
Returns a single user by ID.

### POST /api/auth/userinfo
Creates a new user with the provided information.

### DELETE /api/auth/userinfo/{id}
Deletes the user with the specified ID.

## Response Format

The API returns user data in JSON format with the following fields:

- `id`: Unique identifier (Long)
- `email`: User's email address (String)
- `name`: User's full name (String)
- `major`: User's field of study (String)
- `graduationDate`: Graduation date in ISO format (YYYY-MM-DD)
- `role`: User's role in the system (String)

## Security Improvements

The API uses a UserResponseDTO that excludes sensitive information like password hashes from the response. This is an important security practice to prevent potential exposure of sensitive data.

## Sample Response

```json
{
  "id": 1,
  "email": "user1@example.com",
  "name": "John Doe",
  "major": "Computer Science",
  "graduationDate": "2023-05-15",
  "role": "STUDENT"
}
```

## Implementation Details

The API is implemented using Spring Boot with the following components:

1. **UserInfoController**: Handles HTTP requests and returns appropriate responses
2. **UserService**: Contains business logic for user management
3. **UserRepository**: Provides data access to the user database
4. **UserResponseDTO**: Data Transfer Object that excludes sensitive information from responses

The API is secured using Spring Security and allows public access to all endpoints under `/api/auth/userinfo/**`.