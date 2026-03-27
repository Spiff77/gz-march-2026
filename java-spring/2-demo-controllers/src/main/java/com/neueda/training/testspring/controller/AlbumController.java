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
@CrossOrigin
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("")
    public ResponseEntity<List<Album>> getAlbum() {
        List<Album> albums = albumRepository.findAll();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/tracks/{numberOfTracks}")
    public ResponseEntity<List<Album>> getAlbumByNumberOfTracks(@PathVariable int numberOfTracks) {
        List<Album> albums = albumRepository.findByNumberOfTracks(numberOfTracks);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            return ResponseEntity.ok(album.get());
            // or return new ResponseEntity<>(album.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
           // or return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album albumDetails) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            Album existingAlbum = album.get();
            existingAlbum.setNumberOfTracks(albumDetails.getNumberOfTracks());
            existingAlbum.setTitle(albumDetails.getTitle());
            Album updatedAlbum = albumRepository.save(existingAlbum);
            return ResponseEntity.ok(updatedAlbum);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album savedAlbum = albumRepository.save(album);
        return new ResponseEntity<>(savedAlbum, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
