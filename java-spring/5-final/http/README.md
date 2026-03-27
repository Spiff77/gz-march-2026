# HTTP REST Client Files

These files can be used directly in IntelliJ IDEA to test the REST API endpoints.

## How to Use

1. Make sure your Spring Boot application is running on `http://localhost:8080`
2. Open any `.http` file in IntelliJ
3. Click the green play button (▶) next to each request to execute it
4. View the response in the bottom panel

## Files

- `artists.http` - Test endpoints for Artist operations
- `albums.http` - Test endpoints for Album operations

## Recommended Testing Flow

1. **Create artists first** (artists.http - Create artist requests)
2. **Add albums to artists** (artists.http - Add album to artist)
3. **Test retrieval** (Get all artists/albums to see relationships)
4. **Test business logic** (Try to delete an artist that has albums - should return 409 CONFLICT)
5. **Test updates** (Update artist/album details)
6. **Clean up** (Delete albums first, then artists)

## Key Endpoints

### Artists
- `GET /artists` - Get all artists with their albums
- `GET /artists/{id}` - Get specific artist
- `POST /artists` - Create new artist
- `PUT /artists/{id}` - Update artist
- `DELETE /artists/{id}` - Delete artist (fails if has albums)
- `POST /artists/{id}/albums` - Add album to artist (manages relationship)

### Albums
- `GET /albums` - Get all albums
- `GET /albums/{id}` - Get specific album
- `POST /albums` - Create new album
- `PUT /albums/{id}` - Update album
- `DELETE /albums/{id}` - Delete album

## Notes

- The `POST /artists/{artistId}/albums` endpoint automatically sets up the bidirectional relationship
- Deleting an artist with albums will return HTTP 409 (Conflict)
- When creating albums via `POST /albums`, you can include an artist reference by ID
