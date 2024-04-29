package com.example.CROUD.service

import com.example.CROUD.cliente.OpenLibraryClient
import com.example.CROUD.factory.LivroModelFactory
import com.example.CROUD.model.DTO.livroDTO
import com.example.CROUD.model.Livro
import com.example.CROUD.repository.LivroRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class LivroService(@Valid
                   private val livroRepository: LivroRepository,
                   val openLibraryClient: OpenLibraryClient,
                   val livroModelFactory: LivroModelFactory
){

    fun inserirLivro(livro: livroDTO): Livro {
        val livroApiExterna = openLibraryClient.searchBooks(livro.titulo)//busca o livro na api externa
        val livroApiInterna = livroModelFactory.createInstance(livroApiExterna)//cria um livro com os dados da api externa

        if (livroRepository.existsByTitulo(livroApiInterna.titulo)){
            throw IllegalArgumentException("Livro já cadastrado")
        }
        return livroRepository.save(livroApiInterna)

    }
    fun removerLivro(id: Long){
        if (!livroRepository.existsById(id)) {
//            throw NoSuchElementException("Livro não encontrado")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado")

        }
        return livroRepository.deleteById(id)
    }
    fun removerTodosLivros(){
        if (livroRepository.findAll().isEmpty()){//findall retorna uma lista de livros
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Não há livros para deletar")
        }
        return livroRepository.deleteAll()
    }
        fun listarTodosLivros(): List<Livro>{
            if (livroRepository.findAll().isEmpty()){//findall retorna uma lista de livros
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Não há livros para listar")
            }
        return livroRepository.findAll()
    }
    fun listarLivro(id: Long): Livro{
        return livroRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado") }
    }
    fun editarLivro(id: Long, livro: Livro): Livro? {
        val livroExistente: Livro = livroRepository.findById(id).orElse(null) ?: return null

        if (livroRepository.existsByTituloAndIdNot(livro.titulo, id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Título repetido")
        }

        if (livro.titulo.isNotBlank()) {
            livroExistente.titulo = livro.titulo
        }

        if (livro.descricao.isNotBlank()) {
            livroExistente.descricao = livro.descricao
        }

//        if (livro.autores.isNotEmpty()) {
//            livroExistente.autores = livro.autores
//        }
        return livroRepository.save(livroExistente)
    }
}