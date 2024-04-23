package com.example.CROUD.model.DTO.OpenLibrary

import com.fasterxml.jackson.annotation.JsonProperty

data class DocsDTO (
    var title: Any? = null,
    var subtitle: Any? = null,
    @JsonProperty("author_name")
    var author: List<String>? = null
)