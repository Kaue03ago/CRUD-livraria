package com.example.CROUD.model.DTO.OpenLibrary

import com.fasterxml.jackson.annotation.JsonProperty

data class DocsDTO (
    var title: Any? = null,
    @JsonProperty("first_sentence")
    var subtitle: Any? = null,
    @JsonProperty("author_alternative_name")
    var author: List<String>? = null
)