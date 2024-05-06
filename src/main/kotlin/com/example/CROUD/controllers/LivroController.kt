package com.example.CROUD.controllers

import com.example.CROUD.model.DTO.livroDTO
import com.example.CROUD.model.Livro
import com.example.CROUD.service.LivroService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Valid
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
    fun inserirLivro(@RequestBody @Valid livro: livroDTO): ResponseEntity<Livro> {//
//        alterarTamanhoDescricao()
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
    @PutMapping("/editar/{id}")
    fun editarLivro(@PathVariable id: Long, @RequestBody livro: Livro): ResponseEntity<Livro> {
        val livroEditado = livroService.editarLivro(id, livro)
        return if (livroEditado != null) {
            ResponseEntity.ok(livroEditado)
        } else
            ResponseEntity.notFound().build()
    }
}