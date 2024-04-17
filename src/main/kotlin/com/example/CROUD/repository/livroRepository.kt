package com.example.CROUD.repository

import com.example.CROUD.model.Livro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LivroRepository : JpaRepository<Livro, Long> {
    fun deleteByCategoria(categoria: String)//
    fun existsByTitulo(titulo: String): Boolean//verifica se existe um livro com o titulo passado
}