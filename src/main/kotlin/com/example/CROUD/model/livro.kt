package com.example.CROUD.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Livro (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long=0,
    var titulo: String,
    var categoria: String




    )