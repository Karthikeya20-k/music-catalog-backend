package com.karthik.musiccatalog.service;

import com.karthik.musiccatalog.entity.Song;
import com.karthik.musiccatalog.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.karthik.musiccatalog.entity.User;
import com.karthik.musiccatalog.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository repository;
    private final UserRepository userRepository;

    public Song saveSong(Song song) {

    User user = getCurrentUser();

    if (repository.existsByTrackIdAndUser(song.getTrackId(), user)) {
        throw new RuntimeException("Song already exists in library.");
    }

    song.setUser(user);
    song.setCreatedAt(LocalDateTime.now());
    song.setUpdatedAt(LocalDateTime.now());

    return repository.save(song);
}

    public List<Song> getAllSongs() {

    User user = getCurrentUser();

    return repository.findByUser(user);
}

    public void deleteSong(Long trackId) {

    User user = getCurrentUser();

    repository.deleteByTrackIdAndUser(trackId, user);
}

    public List<Song> getFavoriteSongs() {

    User user = getCurrentUser();

    return repository.findByFavoriteTrueAndUser(user);
}

    public Song toggleFavorite(Long trackId) {

        User user = getCurrentUser();

        Song song = repository.findByTrackIdAndUser(trackId, user)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        song.setFavorite(!song.getFavorite());
        song.setUpdatedAt(LocalDateTime.now());

        return repository.save(song);
    }

    public List<Song> searchSongs(String query) {

    User user = getCurrentUser();

    return repository.searchSongs(user, query);
}

    public Song updateRating(Long trackId, Integer rating) {

        User user = getCurrentUser();

        Song song = repository.findByTrackIdAndUser(trackId, user)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        song.setUserRating(rating);
        song.setUpdatedAt(LocalDateTime.now());

        return repository.save(song);
    }

    public Song updateNotes(Long trackId, String notes) {

        User user = getCurrentUser();

        Song song = repository.findByTrackIdAndUser(trackId, user)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        song.setUserNotes(notes);
        song.setUpdatedAt(LocalDateTime.now());

        return repository.save(song);
    }
    private User getCurrentUser() {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
}

}