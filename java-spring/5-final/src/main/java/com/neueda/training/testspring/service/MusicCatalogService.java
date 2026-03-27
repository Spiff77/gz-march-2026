package com.neueda.training.testspring.service;

import com.neueda.training.testspring.model.entity.Album;
import com.neueda.training.testspring.model.entity.Artist;
import com.neueda.training.testspring.repository.AlbumRepository;
import com.neueda.training.testspring.repository.ArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MusicCatalogService {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public MusicCatalogService(ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    // Artist operations
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Optional<Artist> updateArtist(Long id, Artist artistDetails) {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        if (artistOptional.isPresent()) {
            Artist existingArtist = artistOptional.get();
            existingArtist.setName(artistDetails.getName());
            existingArtist.setDescription(artistDetails.getDescription());
            return Optional.of(artistRepository.save(existingArtist));
        }
        return Optional.empty();
    }

    public boolean deleteArtist(Long id) {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        if (artistOptional.isEmpty()) {
            return false;
        }

        Artist artist = artistOptional.get();
        // Business rule: prevent deletion if artist has albums
        if (!artist.getAlbums().isEmpty()) {
            throw new IllegalStateException("Cannot delete artist with existing albums. Artist has " +
                artist.getAlbums().size() + " album(s).");
        }

        artistRepository.deleteById(id);
        return true;
    }

    // Album operations
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Transactional
    public Album createAlbumForArtist(Long artistId, Album album) {
        Optional<Artist> artistOptional = artistRepository.findById(artistId);
        if (artistOptional.isEmpty()) {
            throw new IllegalArgumentException("Artist not found with id: " + artistId);
        }

        Artist artist = artistOptional.get();
        // Manage bidirectional relationship
        album.setArtist(artist);
        Album savedAlbum = albumRepository.save(album);
        artist.getAlbums().add(savedAlbum);

        return savedAlbum;
    }

    public Optional<Album> updateAlbum(Long id, Album albumDetails) {
        Optional<Album> albumOptional = albumRepository.findById(id);
        if (albumOptional.isPresent()) {
            Album existingAlbum = albumOptional.get();
            existingAlbum.setTitle(albumDetails.getTitle());
            existingAlbum.setNumberOfTracks(albumDetails.getNumberOfTracks());
            existingAlbum.setArtist(albumDetails.getArtist());
            return Optional.of(albumRepository.save(existingAlbum));
        }
        return Optional.empty();
    }

    public boolean deleteAlbum(Long id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
