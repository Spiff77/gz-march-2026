package com.neueda.training.testspring.model.entity;

import jakarta.persistence.*;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int numberOfTracks;

    @ManyToOne(fetch = FetchType.LAZY)
    Artist artist;

}
