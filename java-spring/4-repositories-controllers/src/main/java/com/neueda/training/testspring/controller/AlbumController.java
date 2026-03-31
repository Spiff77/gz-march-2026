package com.neueda.training.testspring.controller;

import com.neueda.training.testspring.model.entity.Album;
import com.neueda.training.testspring.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            return ResponseEntity.ok(album.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album savedCompactDisc = albumRepository.save(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompactDisc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album albumDetails) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            Album existingAlbum = album.get();
            existingAlbum.setTitle(albumDetails.getTitle());
            existingAlbum.setNumberOfTracks(albumDetails.getNumberOfTracks());
            existingAlbum.setArtist(albumDetails.getArtist());
            Album updatedCompactDisc = albumRepository.save(existingAlbum);
            return ResponseEntity.ok(updatedCompactDisc);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
