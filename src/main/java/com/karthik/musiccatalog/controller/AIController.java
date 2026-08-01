package com.karthik.musiccatalog.controller;

import com.karthik.musiccatalog.entity.Song;
import com.karthik.musiccatalog.entity.User;
import com.karthik.musiccatalog.repository.SongRepository;
import com.karthik.musiccatalog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AIController {

    private final SongRepository repository;
    private final UserRepository userRepository;

    @GetMapping("/recommend")
    public Map<String, Object> recommend() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Song> songs = repository.findByUser(user);

        if (songs.isEmpty()) {

            Map<String, Object> result = new HashMap<>();

            result.put("favoriteGenre", "No Songs");
            result.put("message", "Add some songs to your library first.");
            result.put("recommendations", new ArrayList<>());

            return result;
        }

        Map<String, Long> genreCount = new HashMap<>();

        for (Song song : songs) {

            genreCount.put(
                    song.getPrimaryGenreName(),
                    genreCount.getOrDefault(song.getPrimaryGenreName(), 0L) + 1
            );

        }

        String favoriteGenre = "Unknown";
        long max = 0;

        for (Map.Entry<String, Long> entry : genreCount.entrySet()) {

            if (entry.getValue() > max) {

                max = entry.getValue();
                favoriteGenre = entry.getKey();

            }

        }

        List<String> recommendations;

        switch (favoriteGenre.toLowerCase()) {

            case "alternative" ->
                    recommendations = List.of("Imagine Dragons", "OneRepublic", "Keane", "The Script");

            case "pop" ->
                    recommendations = List.of("Ed Sheeran", "Taylor Swift", "Dua Lipa", "Shawn Mendes");

            case "rock" ->
                    recommendations = List.of("Linkin Park", "Green Day", "Bon Jovi", "Queen");

            case "telugu" ->
                    recommendations = List.of("Anirudh Ravichander", "Thaman S", "Devi Sri Prasad", "Sid Sriram");

            case "tamil" ->
                    recommendations = List.of("Anirudh Ravichander", "A.R. Rahman", "Yuvan Shankar Raja", "Harris Jayaraj");

            default ->
                    recommendations = List.of("Coldplay", "Adele", "Bruno Mars", "Maroon 5");

        }

        Map<String, Object> result = new HashMap<>();

        result.put("favoriteGenre", favoriteGenre);
        result.put("message", "You mostly listen to " + favoriteGenre + " music.");
        result.put("recommendations", recommendations);

        return result;
    }
}