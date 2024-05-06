package com.example.CROUD.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity(name = "livro")
data class Livro(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @field: NotBlank
    @field: NotNull
    @field: Size(min = 3, max = 75)
    @Column
    var titulo: String,

  @Column
    var descricao: String,


    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "livro_id")
    var autores: List<Autor>?,

//    @field: NotBlank
    @field: NotNull
    @Column
    var valor: Double
)