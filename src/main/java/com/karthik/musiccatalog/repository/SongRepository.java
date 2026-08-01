package com.karthik.musiccatalog.repository;

import com.karthik.musiccatalog.entity.Song;
import com.karthik.musiccatalog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    // ==========================
    // User Library
    // ==========================

    List<Song> findByUser(User user);

    Optional<Song> findByTrackIdAndUser(Long trackId, User user);

    boolean existsByTrackIdAndUser(Long trackId, User user);

    void deleteByTrackIdAndUser(Long trackId, User user);

    List<Song> findByFavoriteTrueAndUser(User user);

    // ==========================
    // Analytics
    // ==========================

    @Query("""
        SELECT COUNT(s)
        FROM Song s
        WHERE s.user = :user
    """)
    Long totalSongs(User user);

    @Query("""
        SELECT COUNT(DISTINCT s.artistName)
        FROM Song s
        WHERE s.user = :user
    """)
    Long totalArtists(User user);

    @Query("""
        SELECT COUNT(DISTINCT s.primaryGenreName)
        FROM Song s
        WHERE s.user = :user
    """)
    Long totalGenres(User user);

    @Query("""
        SELECT s.primaryGenreName, COUNT(s)
        FROM Song s
        WHERE s.user = :user
        GROUP BY s.primaryGenreName
    """)
    List<Object[]> songsByGenre(User user);

    @Query("""
        SELECT s.artistName, COUNT(s)
        FROM Song s
        WHERE s.user = :user
        GROUP BY s.artistName
        ORDER BY COUNT(s) DESC
    """)
    List<Object[]> songsByArtist(User user);

    // ==========================
    // Search
    // ==========================

    @Query("""
        SELECT s
        FROM Song s
        WHERE s.user = :user
          AND (
                LOWER(s.trackName) LIKE LOWER(CONCAT('%', :query, '%'))
             OR LOWER(s.artistName) LIKE LOWER(CONCAT('%', :query, '%'))
             OR LOWER(s.collectionName) LIKE LOWER(CONCAT('%', :query, '%'))
             OR LOWER(s.primaryGenreName) LIKE LOWER(CONCAT('%', :query, '%'))
          )
    """)
    List<Song> searchSongs(User user, String query);

    // ==========================
    // Rating Analytics
    // ==========================

    @Query("""
        SELECT s.userRating, COUNT(s)
        FROM Song s
        WHERE s.user = :user
        GROUP BY s.userRating
        ORDER BY s.userRating
    """)
    List<Object[]> ratingChart(User user);

    @Query("""
        SELECT DATE(s.createdAt), COUNT(s)
        FROM Song s
        WHERE s.user = :user
        GROUP BY DATE(s.createdAt)
        ORDER BY DATE(s.createdAt)
    """)
    List<Object[]> dateChart(User user);

    @Query("""
        SELECT AVG(s.userRating)
        FROM Song s
        WHERE s.user = :user
    """)
    Double averageRating(User user);

}