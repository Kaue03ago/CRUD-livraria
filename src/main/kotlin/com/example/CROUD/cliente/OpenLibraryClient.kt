package com.example.CROUD.cliente

import com.example.CROUD.model.DTO.OpenLibrary.OpenLibraryResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(name = "openLibrary", url = "https://openlibrary.org/")
interface OpenLibraryClient {
    @GetMapping("/search.json?q={title}")
    fun searchBooks(@PathVariable title: String): OpenLibraryResponse
}