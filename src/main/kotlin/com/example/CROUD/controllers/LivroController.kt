package com.example.CROUD.controllers

import com.example.CROUD.model.DTO.livroDTO
import com.example.CROUD.model.Livro
import com.example.CROUD.service.LivroService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity


@RestController
@RequestMapping("/livraria")


class LivroController(private val livroService: LivroService) {
// poderia ter instaciado o liroservice em escopo global, assim utilizando em todos os metodos
    //evitando duplicação de código

    @PostMapping("/inserir")
    fun inserirLivro(@RequestBody livro: livroDTO): ResponseEntity<Livro> {//
        return ResponseEntity(livroService.inserirLivro(livro), HttpStatus.CREATED)
    }

    @DeleteMapping("/deletar/{id}")
    fun removerLivro(@PathVariable id: Long): ResponseEntity<Void> {
        livroService.removerLivro(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/deletarTodos")
    fun removerTodosLivros(): ResponseEntity<Void> {
        livroService.removerTodosLivros()
        return ResponseEntity(HttpStatus.OK)
    }

//    @DeleteMapping("/deletarCategoria/{categoria}")
//    fun removerCategoria(@PathVariable categoria: String): ResponseEntity<Void> {
//        livroService.removerCategoria(categoria)
//        return ResponseEntity(HttpStatus.OK)
//    }


    @GetMapping("/listarTodosLivros")
    fun listarTodosLivros(): ResponseEntity<List<Livro>> {
        if (livroService.listarTodosLivros().isEmpty()) {
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity(livroService.listarTodosLivros(), HttpStatus.OK)
    }

    @GetMapping("/listarLivro/{id}")
    fun listarLivro(@PathVariable id: Long): ResponseEntity<Livro> {
        return ResponseEntity.ok(livroService.listarLivro(id))
    }


    @GetMapping("/listarLivroNome/{titulo}")
    fun listarLivroNome(@PathVariable titulo: String): ResponseEntity<*>{
        val restTemplate = RestTemplate()
        val url = restTemplate.getForEntity<String>("https://openlibrary.org/search.json?q=${titulo.replace(" ", "+")}")

        // Faz o parse do json retornado pela api usando o jackson
        val objectMapper = ObjectMapper()
        val response = objectMapper.readTree(url.body)



        if (response != null && response.has("docs")) {
            val docs = response["docs"]
            if (docs.isArray && docs.size() > 0) {
                val firstDoc = docs[0]
                if (firstDoc.has("author_name")) {
                    val authors = firstDoc["author_name"]
                    if (authors.isArray && authors.size() > 0) {
                        val author = authors[0].asText()
                        return ResponseEntity.ok(author)
                    }
                }
            }
        }

//        // Extrai os títulos dos livros do JSON
//        val livros = response["docs"].map {
//            Livro2(
//                titulo = it["title"].asText(),
//                autor = it["author_name"].map { autor -> autor.asText() }
//            )
//        }


        return ResponseEntity.ok(url.body)

    }

    @PutMapping("/editar/{id}")
    fun editarLivro(@PathVariable id: Long, @RequestBody livro: Livro): ResponseEntity<Livro> {
        val livroEditado = livroService.editarLivro(id, livro)
        return if (livroEditado != null) {
            ResponseEntity.ok(livroEditado)
        } else
            ResponseEntity.notFound().build()
    }
}