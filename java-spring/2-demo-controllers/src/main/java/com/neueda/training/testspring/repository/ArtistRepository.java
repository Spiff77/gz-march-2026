package com.neueda.training.testspring.repository;

import com.neueda.training.testspring.model.entity.Album;
import com.neueda.training.testspring.model.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    public List<Artist> findByNameStartingWith(String name);

    @Query("Select a from Artist a WHERE LENGTH(a.description) = ?1 ")
    public List<Artist> getByDescriptionLength(int length);

}
