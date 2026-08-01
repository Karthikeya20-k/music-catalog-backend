package com.karthik.musiccatalog.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResult {

    private Long trackId;

    private String trackName;

    private String artistName;

    private String collectionName;

    private String artworkUrl100;

    private String primaryGenreName;

}