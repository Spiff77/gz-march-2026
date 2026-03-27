package com.neueda.training.testspring.controller;

import com.neueda.training.testspring.model.entity.Album;
import com.neueda.training.testspring.model.entity.Artist;
import com.neueda.training.testspring.service.MusicCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final MusicCatalogService musicCatalogService;

    public ArtistController(MusicCatalogService musicCatalogService) {
        this.musicCatalogService = musicCatalogService;
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return musicCatalogService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Optional<Artist> artist = musicCatalogService.getArtistById(id);
        return artist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist savedArtist = musicCatalogService.createArtist(artist);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artistDetails) {
        Optional<Artist> updatedArtist = musicCatalogService.updateArtist(id, artistDetails);
        return updatedArtist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        try {
            boolean deleted = musicCatalogService.deleteArtist(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/{artistId}/albums")
    public ResponseEntity<Album> addAlbumToArtist(@PathVariable Long artistId, @RequestBody Album album) {
        try {
            Album savedAlbum = musicCatalogService.createAlbumForArtist(artistId, album);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAlbum);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
