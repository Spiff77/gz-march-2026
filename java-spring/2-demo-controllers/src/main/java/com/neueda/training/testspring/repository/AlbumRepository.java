package com.neueda.training.testspring.repository;

import com.neueda.training.testspring.model.entity.Album;
import com.neueda.training.testspring.model.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    public List<Album> findByNumberOfTracks(int numberOfTracks);
}
