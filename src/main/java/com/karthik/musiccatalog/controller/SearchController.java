package com.karthik.musiccatalog.controller;

import com.karthik.musiccatalog.service.ItunesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final ItunesService service;

    @GetMapping
    public String search(@RequestParam String query){

        return service.searchSongs(query);

    }

}