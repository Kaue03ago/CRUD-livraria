package com.example.CROUD.controllers

import com.example.CROUD.model.Livro
import com.example.CROUD.service.LivroService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/livraria")


class LivroController (private val livroService: LivroService) {


    @PostMapping("/inserir")
    fun inserirLivro(@RequestBody livro: Livro): ResponseEntity<Livro> {//
        val livroInserido = livroService.inserirLivro(livro)//inserirLivro(livro)
        return ResponseEntity(livroInserido, HttpStatus.CREATED)
    }

    @DeleteMapping("/deletar/{id}")
    fun removerLivro(@PathVariable id: Long): ResponseEntity<Void>{
        livroService.removerLivro(id)
        return ResponseEntity(HttpStatus.OK)
    }
    @DeleteMapping("/deletarTodos")
    fun removerTodosLivros(): ResponseEntity<Void>{



        livroService.removerTodosLivros()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/deletarCategoria/{categoria}")
    fun removerCategoria(@PathVariable categoria: String): ResponseEntity<Void>{
//        if (livroService.listarTodosLivros().isEmpty()){
//            return ResponseEntity(HttpStatus.NOT_FOUND)
//        }

        livroService.removerCategoria(categoria)
        return ResponseEntity(HttpStatus.OK)
    }


    @GetMapping("/listarTodosLivros")
    fun listarTodosLivros(): ResponseEntity<List<Livro>>{
    if(livroService.listarTodosLivros().isEmpty()){
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
    return ResponseEntity(livroService.listarTodosLivros(), HttpStatus.OK)
    }

    @GetMapping("/listarLivro/{id}")
    fun listarLivro(@PathVariable id: Long): ResponseEntity<Livro>{
//        livroService.listarLivro(id)
        val auxlivro = livroService.listarLivro(id)
        return ResponseEntity.ok(auxlivro)

    }

    @PutMapping("/editar/{id}")
    fun editarLivro(@PathVariable id: Long, @RequestBody livro: Livro): ResponseEntity<Livro>{

    val livroEditado = livroService.editarLivro(id, livro)
        return if (livroEditado != null){
            ResponseEntity.ok(livroEditado)
            }else
                ResponseEntity.notFound().build()
    }








}