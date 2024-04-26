package com.example.CROUD.model.DTO

import com.example.CROUD.model.Autor
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class livroDTO(
    @field: NotBlank
    @field: NotNull
    @field: Size(min = 3, max = 75)
    val titulo: String,
    val descricao: String? = null,

//    var autores: List<Autor>

    val autor: String? = null
)
