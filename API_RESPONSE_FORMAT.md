# User Info API Response Format

## GET /api/auth/userinfo
This endpoint returns a list of all users in the system.

### Response Format:
```json
[
  {
    "id": 1,
    "email": "user1@example.com",
    "name": "John Doe",
    "major": "Computer Science",
    "graduationDate": "2023-05-15",
    "role": "STUDENT"
  },
  {
    "id": 2,
    "email": "user2@example.com",
    "name": "Jane Smith",
    "major": "Engineering",
    "graduationDate": "2024-06-20",
    "role": "STUDENT"
  }
]
```

## GET /api/auth/userinfo/{id}
This endpoint returns a single user by ID.

### Response Format:
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

> **Security Note**: The API now uses a UserResponseDTO that excludes sensitive information like password hashes from the response.