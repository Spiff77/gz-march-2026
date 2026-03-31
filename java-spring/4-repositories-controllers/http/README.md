# REST API Test Files

This directory contains HTTP request files for testing the Spring Boot REST API.

## Files

- `hello.http` - Tests for HelloController endpoints
- `artists.http` - CRUD operations for Artist resources
- `compact-discs.http` - CRUD operations for CompactDisc resources

## How to Use

### IntelliJ IDEA / VS Code with REST Client Extension

1. Open any `.http` file
2. Click the "Run" button next to each request
3. View the response in the editor

### Using curl (alternative)

You can convert these requests to curl commands:

```bash
# Example: Get all artists
curl http://localhost:8080/artists

# Example: Create an artist
curl -X POST http://localhost:8080/artists \
  -H "Content-Type: application/json" \
  -d '{"name": "The Beatles", "description": "Legendary British rock band"}'
```

## Testing Workflow

1. Start your Spring Boot application
2. First, create some Artists using `artists.http`
3. Then create CompactDiscs and link them to Artists using `compact-discs.http`
4. Test the basic endpoints using `hello.http`

## Notes

- Make sure your Spring Boot application is running on `http://localhost:8080`
- The database is configured with `spring.jpa.hibernate.ddl-auto=create-drop`, so data will be lost when the application restarts
- Artist ID references in CompactDisc requests should match existing Artist IDs
