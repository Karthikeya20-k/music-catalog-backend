package com.karthik.musiccatalog.controller;

import com.karthik.musiccatalog.entity.Song;
import com.karthik.musiccatalog.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService service;

    @PostMapping
    public Song save(@RequestBody Song song) {
        return service.saveSong(song);
    }

    @GetMapping
    public List<Song> getAll() {
        return service.getAllSongs();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteSong(id);
        return "Song Deleted Successfully";
    }

    @PutMapping("/{id}/favorite")
    public Song favorite(@PathVariable Long id) {

    return service.toggleFavorite(id);

}

    @GetMapping("/search")
    public List<Song> searchSongs(@RequestParam String query) {

    return service.searchSongs(query);

    }

    @PutMapping("/{id}/rating")
    public Song updateRating(
        @PathVariable Long id,
        @RequestParam Integer rating) {

    return service.updateRating(id, rating);

    }

    

    @PutMapping("/{id}/notes")
    public Song updateNotes(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return service.updateNotes(id, body.get("notes"));

    }
}