package com.example.CROUD.model.DTO.OpenLibrary

data class OpenLibraryResponse (
    var numFound: Int? = null,
    var start: Int? = null,
    var numFoundExact: Boolean,
    var docs: List<DocsDTO>
)