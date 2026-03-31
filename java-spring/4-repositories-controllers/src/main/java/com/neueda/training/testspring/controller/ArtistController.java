package com.neueda.training.testspring.controller;

import com.neueda.training.testspring.model.entity.Artist;
import com.neueda.training.testspring.repository.ArtistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            return ResponseEntity.ok(artist.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist savedArtist = artistRepository.save(artist);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArtist);
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
