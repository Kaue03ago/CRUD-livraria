//package com.example.CROUD.controllers
//
//import com.example.CROUD.model.Categoria
//import com.example.CROUD.service.CategoriaService
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/categorias")
//
//
//class CategoriaController (private val categoriaService: CategoriaService) {
//
//
//    @PostMapping("/inserir")
//    fun inserirCategoria(@RequestBody categoria: Categoria): ResponseEntity<Categoria> {
//        val categoriaInserida = categoriaService.inserirCategoria(categoria)
//        return ResponseEntity(categoriaInserida, HttpStatus.CREATED)
//    }
//
//
//
//}