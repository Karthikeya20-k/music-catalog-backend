package com.karthik.musiccatalog.controller;

import com.karthik.musiccatalog.entity.User;
import com.karthik.musiccatalog.repository.SongRepository;
import com.karthik.musiccatalog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AnalyticsController {

    private final SongRepository repository;
    private final UserRepository userRepository;

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public Map<String, Object> analytics() {

        User user = getCurrentUser();

        Map<String, Object> map = new HashMap<>();

        map.put("totalSongs", repository.totalSongs(user));
        map.put("totalArtists", repository.totalArtists(user));
        map.put("totalGenres", repository.totalGenres(user));
        map.put("favoriteSongs", repository.findByFavoriteTrueAndUser(user).size());
        map.put("averageRating", repository.averageRating(user));

        List<Map<String, Object>> genreData = new ArrayList<>();

        for (Object[] row : repository.songsByGenre(user)) {

            Map<String, Object> item = new HashMap<>();

            item.put("genre", row[0]);
            item.put("count", row[1]);

            genreData.add(item);
        }

        map.put("genreChart", genreData);

        List<Map<String, Object>> artistData = new ArrayList<>();

        for (Object[] row : repository.songsByArtist(user)) {

            Map<String, Object> item = new HashMap<>();

            item.put("artist", row[0]);
            item.put("count", row[1]);

            artistData.add(item);
        }

        map.put("artistChart", artistData);
        map.put("ratingChart", repository.ratingChart(user));
        map.put("dateChart", repository.dateChart(user));

        return map;
    }
}