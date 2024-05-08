package com.example.CROUD.repository

import com.example.CROUD.model.Autor
import com.example.CROUD.model.Livro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LivroRepository : JpaRepository<Livro, Long> {
    fun existsByTitulo(titulo: String): Boolean//verifica se existe um livro com o titulo passado
    fun existsByTituloAndIdNot(titulo: String, id: Long): Boolean

//    fun findAllTitulo(titulo: String): List<Livro>



//    fun findAllAutores(): List<Autor>

}