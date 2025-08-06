# User Info API Summary

## Response Format

The User Info API returns user data in JSON format with the following fields:

- `id`: Unique identifier (Long)
- `email`: User's email address (String)
- `password`: Encrypted password hash (String)
- `name`: User's full name (String)
- `major`: User's field of study (String)
- `graduationDate`: Graduation date in ISO format (YYYY-MM-DD)
- `role`: User's role in the system (String)

## Security Note

The current implementation returns the password hash in the response, which is not recommended for production environments. Consider creating a DTO for API responses that excludes sensitive information.