package com.example.CROUD.model.DTO

import com.example.CROUD.model.Autor
import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class livroDTO(
    @field: NotBlank
    @field: NotNull
    @field: Size(min = 2, max = 75)
    val titulo: String,
    val descricao: String? = null,
    val autor: String? = null,
    val valor: Double,
    val anoPublicacao: Int?,
    val paginas: Int?,
    val linguagens: List<String>?,
    var peso: Double,
    var largura: Double,
    var altura: Double,
    var profundidade: Double,
    var colaboradores: List<String>?,
    var generos: List<String>?

)
