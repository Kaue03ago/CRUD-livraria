package com.example.CROUD.service

import com.example.CROUD.model.Livro
import com.example.CROUD.repository.LivroRepository
import org.springframework.stereotype.Service


@Service
class LivroService(private val livroRepository: LivroRepository){

    fun inserirLivro(livro: Livro): Livro {
        return livroRepository.save(livro)//save é um método do JpaRepository q salva
    // o objeto no banco de dados
    }
}