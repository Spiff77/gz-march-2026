package com.neueda.training.testspring.controller;

import com.neueda.training.testspring.model.entity.Artist;
import com.neueda.training.testspring.service.MusicCatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MusicCatalogService musicCatalogService;

    private ObjectMapper objectMapper;
    private Artist sampleArtist;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        sampleArtist = new Artist();
        sampleArtist.setId(1L);
        sampleArtist.setName("Radiohead");
        sampleArtist.setDescription("English rock band");
    }

    @Test
    void getAllArtists_returnsArtistList() throws Exception {
        Artist artist2 = new Artist();
        artist2.setId(2L);
        artist2.setName("Nirvana");

        when(musicCatalogService.getAllArtists()).thenReturn(Arrays.asList(sampleArtist, artist2));

        mockMvc.perform(get("/artists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Radiohead")))
                .andExpect(jsonPath("$[1].name", is("Nirvana")));
    }

    @Test
    void getArtistById_existingId_returnsArtist() throws Exception {
        when(musicCatalogService.getArtistById(1L)).thenReturn(Optional.of(sampleArtist));

        mockMvc.perform(get("/artists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Radiohead")))
                .andExpect(jsonPath("$.description", is("English rock band")));
    }

    @Test
    void getArtistById_nonExistingId_returns404() throws Exception {
        when(musicCatalogService.getArtistById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/artists/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createArtist_returns201() throws Exception {
        when(musicCatalogService.createArtist(any(Artist.class))).thenReturn(sampleArtist);

        mockMvc.perform(post("/artists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleArtist)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Radiohead")));
    }

    @Test
    void updateArtist_existingId_returnsUpdatedArtist() throws Exception {
        Artist updated = new Artist();
        updated.setId(1L);
        updated.setName("Updated");
        updated.setDescription("Updated desc");

        when(musicCatalogService.updateArtist(eq(1L), any(Artist.class))).thenReturn(Optional.of(updated));

        mockMvc.perform(put("/artists/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated")));
    }

    @Test
    void updateArtist_nonExistingId_returns404() throws Exception {
        when(musicCatalogService.updateArtist(eq(99L), any(Artist.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/artists/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleArtist)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteArtist_existingId_returns204() throws Exception {
        when(musicCatalogService.deleteArtist(1L)).thenReturn(true);

        mockMvc.perform(delete("/artists/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteArtist_nonExistingId_returns404() throws Exception {
        when(musicCatalogService.deleteArtist(99L)).thenReturn(false);

        mockMvc.perform(delete("/artists/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteArtist_withAlbums_returns409() throws Exception {
        when(musicCatalogService.deleteArtist(1L)).thenThrow(new IllegalStateException("Has albums"));

        mockMvc.perform(delete("/artists/1"))
                .andExpect(status().isConflict());
    }
}
