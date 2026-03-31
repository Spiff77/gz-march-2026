package com.neueda.training.testspring.service;

import com.neueda.training.testspring.model.entity.Album;
import com.neueda.training.testspring.model.entity.Artist;
import com.neueda.training.testspring.repository.AlbumRepository;
import com.neueda.training.testspring.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MusicCatalogServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private MusicCatalogService musicCatalogService;

    private Artist sampleArtist;
    private Album sampleAlbum;

    @BeforeEach
    void setUp() {
        sampleArtist = new Artist();
        sampleArtist.setId(1L);
        sampleArtist.setName("Radiohead");
        sampleArtist.setDescription("English rock band");

        sampleAlbum = new Album();
        sampleAlbum.setId(1L);
        sampleAlbum.setTitle("OK Computer");
        sampleAlbum.setNumberOfTracks(12);
    }

    // Artist tests

    @Test
    void getAllArtists_returnsAllArtists() {
        Artist artist2 = new Artist();
        artist2.setId(2L);
        artist2.setName("Nirvana");

        when(artistRepository.findAll()).thenReturn(Arrays.asList(sampleArtist, artist2));

        List<Artist> result = musicCatalogService.getAllArtists();

        assertEquals(2, result.size());
        verify(artistRepository).findAll();
    }

    @Test
    void getArtistById_existingId_returnsArtist() {
        when(artistRepository.findById(1L)).thenReturn(Optional.of(sampleArtist));

        Optional<Artist> result = musicCatalogService.getArtistById(1L);

        assertTrue(result.isPresent());
        assertEquals("Radiohead", result.get().getName());
    }

    @Test
    void getArtistById_nonExistingId_returnsEmpty() {
        when(artistRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Artist> result = musicCatalogService.getArtistById(99L);

        assertTrue(result.isEmpty());
    }

    @Test
    void createArtist_returnsSavedArtist() {
        when(artistRepository.save(any(Artist.class))).thenReturn(sampleArtist);

        Artist result = musicCatalogService.createArtist(sampleArtist);

        assertNotNull(result);
        assertEquals("Radiohead", result.getName());
        verify(artistRepository).save(sampleArtist);
    }

    @Test
    void updateArtist_existingId_returnsUpdatedArtist() {
        Artist updatedDetails = new Artist();
        updatedDetails.setName("Updated Name");
        updatedDetails.setDescription("Updated Description");

        when(artistRepository.findById(1L)).thenReturn(Optional.of(sampleArtist));
        when(artistRepository.save(any(Artist.class))).thenReturn(sampleArtist);

        Optional<Artist> result = musicCatalogService.updateArtist(1L, updatedDetails);

        assertTrue(result.isPresent());
        verify(artistRepository).save(sampleArtist);
    }

    @Test
    void updateArtist_nonExistingId_returnsEmpty() {
        Artist updatedDetails = new Artist();
        updatedDetails.setName("Updated");

        when(artistRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Artist> result = musicCatalogService.updateArtist(99L, updatedDetails);

        assertTrue(result.isEmpty());
        verify(artistRepository, never()).save(any());
    }

    @Test
    void deleteArtist_existingIdNoAlbums_returnsTrue() {
        when(artistRepository.findById(1L)).thenReturn(Optional.of(sampleArtist));

        boolean result = musicCatalogService.deleteArtist(1L);

        assertTrue(result);
        verify(artistRepository).deleteById(1L);
    }

    @Test
    void deleteArtist_nonExistingId_returnsFalse() {
        when(artistRepository.findById(99L)).thenReturn(Optional.empty());

        boolean result = musicCatalogService.deleteArtist(99L);

        assertFalse(result);
        verify(artistRepository, never()).deleteById(any());
    }

    @Test
    void deleteArtist_withAlbums_throwsException() {
        sampleArtist.getAlbums().add(sampleAlbum);
        when(artistRepository.findById(1L)).thenReturn(Optional.of(sampleArtist));

        assertThrows(IllegalStateException.class, () -> musicCatalogService.deleteArtist(1L));
        verify(artistRepository, never()).deleteById(any());
    }

    // Album tests

    @Test
    void getAllAlbums_returnsAllAlbums() {
        when(albumRepository.findAll()).thenReturn(List.of(sampleAlbum));

        List<Album> result = musicCatalogService.getAllAlbums();

        assertEquals(1, result.size());
    }

    @Test
    void getAlbumById_existingId_returnsAlbum() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(sampleAlbum));

        Optional<Album> result = musicCatalogService.getAlbumById(1L);

        assertTrue(result.isPresent());
        assertEquals("OK Computer", result.get().getTitle());
    }

    @Test
    void createAlbumForArtist_existingArtist_returnsSavedAlbum() {
        when(artistRepository.findById(1L)).thenReturn(Optional.of(sampleArtist));
        when(albumRepository.save(any(Album.class))).thenReturn(sampleAlbum);

        Album result = musicCatalogService.createAlbumForArtist(1L, sampleAlbum);

        assertNotNull(result);
        verify(albumRepository).save(sampleAlbum);
    }

    @Test
    void createAlbumForArtist_nonExistingArtist_throwsException() {
        when(artistRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> musicCatalogService.createAlbumForArtist(99L, sampleAlbum));
        verify(albumRepository, never()).save(any());
    }

    @Test
    void deleteAlbum_existingId_returnsTrue() {
        when(albumRepository.existsById(1L)).thenReturn(true);

        boolean result = musicCatalogService.deleteAlbum(1L);

        assertTrue(result);
        verify(albumRepository).deleteById(1L);
    }

    @Test
    void deleteAlbum_nonExistingId_returnsFalse() {
        when(albumRepository.existsById(99L)).thenReturn(false);

        boolean result = musicCatalogService.deleteAlbum(99L);

        assertFalse(result);
        verify(albumRepository, never()).deleteById(any());
    }
}
