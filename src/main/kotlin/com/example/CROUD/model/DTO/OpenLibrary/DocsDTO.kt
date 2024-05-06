package com.example.CROUD.model.DTO.OpenLibrary

import com.fasterxml.jackson.annotation.JsonProperty

data class DocsDTO (
    var title: Any? = null,
    @JsonProperty("first_sentence")
    var subtitle: Any? = null,
    @JsonProperty("author_name")
    var author: List<String>? = null,
    @JsonProperty("language")
    var language: List<String>? = null,
    @JsonProperty("first_publish_year")
    var firstPublishYear: Int? = null,
    @JsonProperty("number_of_pages_median")
    var publishDate: Int? = null
)