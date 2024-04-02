package com.example.CROUD.controllers

import com.example.CROUD.model.Livro
import com.example.CROUD.service.LivroService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/livros")


class LivroController (private val livroService: LivroService) {


    @PostMapping("/inserir")
    fun inserirLivro(@RequestBody livro: Livro): ResponseEntity<Livro> {//
        val livroInserido = livroService.inserirLivro(livro)//inserirLivro(livro)
        return ResponseEntity(livroInserido, HttpStatus.CREATED)
    }




}