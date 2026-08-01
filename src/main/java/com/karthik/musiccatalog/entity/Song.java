package com.karthik.musiccatalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Original iTunes Track ID
    private Long trackId;

    private String trackName;

    private String artistName;

    private String collectionName;

    private String artworkUrl100;

    private String primaryGenreName;

    @Builder.Default
    private Boolean favorite = false;

    private LocalDate releaseDate;

    private Integer trackCount;

    @Builder.Default
    private Integer userRating = 0;

    @Column(length = 2000)
    private String userNotes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String previewUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}