package com.example.CROUD.service

import com.example.CROUD.cliente.OpenLibraryClient
import com.example.CROUD.factory.LivroModelFactory
import com.example.CROUD.model.Autor
import com.example.CROUD.model.DTO.livroDTO
import com.example.CROUD.model.Livro
import com.example.CROUD.repository.LivroRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.util.MultiValueMap


@Service
class LivroService(@Valid
                   private val livroRepository: LivroRepository,
                   val openLibraryClient: OpenLibraryClient,
                   val livroModelFactory: LivroModelFactory,
                   private val jdbcTemplate: JdbcTemplate
){

    fun alterarTamanhoDescricao() {
        val sql = "ALTER TABLE livro ALTER COLUMN descricao VARCHAR(1000)"
        jdbcTemplate.execute(sql)
    }

    fun inserirLivro(livro: livroDTO): Livro {
        val livroApiExterna = openLibraryClient.searchBooks(livro.titulo)//busca o livro na api externa
        val livroApiInterna = livroModelFactory.createInstance(livroApiExterna)//cria um livro com os dados da api externa

        if (livroRepository.existsByTitulo(livroApiInterna.titulo)){
            throw IllegalArgumentException("Livro já cadastrado")
        }
//        livroApiInterna.anoPublicacao = livro.anoPublicacao
        livroApiInterna.peso = livro.peso
        livroApiInterna.altura = livro.altura
        livroApiInterna.largura = livro.largura
        livroApiInterna.profundidade = livro.profundidade

        livroApiInterna.valor = livro.valor
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
        if (livroRepository.findAll().isEmpty()){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Não há livros para deletar")
        }
        return livroRepository.deleteAll()
    }
    fun listarTodosLivros(): List<Livro>{
            if (livroRepository.findAll().isEmpty()){
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Não há livros para listar")
            }
        return livroRepository.findAll()
    }
    fun listarLivrosTitulo(): List<String>{
        val listLivros: List<Livro> = livroRepository.findAll()
        val titulos: List<String> = listLivros.map { it.titulo }
        return titulos
    }

//    fun listarTodosAutores(): List<Autor> {
////        if (livroRepository.)
//        return livroRepository.findAllAutores()
//    }
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