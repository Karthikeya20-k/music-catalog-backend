package com.karthik.musiccatalog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItunesService {

    private final RestTemplate restTemplate;

    public ItunesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchSongs(String query){

        String url =
                "https://itunes.apple.com/search?term="
                        + query
                        + "&entity=song&limit=20";

        return restTemplate.getForObject(url,String.class);

    }

}