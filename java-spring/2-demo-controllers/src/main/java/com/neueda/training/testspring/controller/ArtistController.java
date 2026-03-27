package com.neueda.training.testspring.controller;

import com.neueda.training.testspring.model.entity.Artist;
import com.neueda.training.testspring.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getArtist() {
        List<Artist> artists = artistRepository.findAll();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            return ResponseEntity.ok(artist.get());
            // or return new ResponseEntity<>(artist.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
           // or return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/artists/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artistDetails) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            Artist existingArtist = artist.get();
            existingArtist.setName(artistDetails.getName());
            existingArtist.setDescription(artistDetails.getDescription());
            Artist updatedArtist = artistRepository.save(existingArtist);
            return ResponseEntity.ok(updatedArtist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/artists")
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist savedArtist = artistRepository.save(artist);
        return new ResponseEntity<>(savedArtist, HttpStatus.CREATED);
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
