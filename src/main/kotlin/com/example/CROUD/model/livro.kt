package com.example.CROUD.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
data class Livro(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @field: NotBlank
    @field: NotNull
    @field: Size(min = 3, max = 75)
    var titulo: String,

    @field: NotBlank
    @field: NotNull
    @field: Size(min = 3)
    var categoria: String



)