package com.neueda.training.testspring.controller;

import com.neueda.training.testspring.model.entity.Album;
import com.neueda.training.testspring.service.MusicCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final MusicCatalogService musicCatalogService;

    public AlbumController(MusicCatalogService musicCatalogService) {
        this.musicCatalogService = musicCatalogService;
    }

    @GetMapping
    public List<Album> getAllAlbums() {
        return musicCatalogService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = musicCatalogService.getAlbumById(id);
        return album.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album savedAlbum = musicCatalogService.createAlbum(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album albumDetails) {
        Optional<Album> updatedAlbum = musicCatalogService.updateAlbum(id, albumDetails);
        return updatedAlbum.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        boolean deleted = musicCatalogService.deleteAlbum(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
