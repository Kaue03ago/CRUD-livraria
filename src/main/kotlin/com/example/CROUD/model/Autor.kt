package com.example.CROUD.model

import jakarta.persistence.*

@Entity(name = "autor")
data class Autor (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

//    @field: NotBlank
//    @field: NotNull
//    @field: Size(min = 3, max = 75)
    @Column
    var nome: String,
)

