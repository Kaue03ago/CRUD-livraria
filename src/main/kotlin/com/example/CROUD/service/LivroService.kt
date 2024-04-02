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
    fun removerLivro(id: Long){
    return livroRepository.deleteById(id)
    }

    fun removerTodosLivros(){
        return livroRepository.deleteAll()
    }

    fun removerCategoria(categoria: String){
        return livroRepository.deleteByCategoria(categoria)
    }

    fun listarTodosLivros(): List<Livro>{
        return livroRepository.findAll()
    }

    fun listarLivro(id: Long): Livro{
        return livroRepository.findById(id).orElseThrow { NoSuchElementException("Livro não encontrado") }
    }

    fun editarLivro(id: Long, livro: Livro): Livro? {
        return livroRepository.findById(id).map { livroExistente ->
            livroExistente.titulo = livro.titulo
            livroExistente.categoria = livro.categoria

            livroRepository.save(livroExistente)
        }.orElseGet {
            null

        }


    }
}