package com.example.CROUD.controllers.LivroController

import com.example.CROUD.model.Autor
import com.example.CROUD.model.DTO.livroDTO
import com.example.CROUD.model.Livro
import com.example.CROUD.repository.LivroRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.domain.AbstractPersistable_.id

import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.awt.PageAttributes


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@WithMockUser
@ActiveProfiles







class ControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var repository: LivroRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() = repository.deleteAll()

    @AfterEach
    fun tearDown() = repository.deleteAll()


    @Test
    fun `listar todos corretamente`(){
        var livroAux  = Livro(1L, "teste titulo", "Teste descricao", null)
        repository.save(livroAux)
//        val aux2 = repository.save(livroAux2)

        mvc.perform(get("/livraria/listarTodosLivros"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(livroAux.id))
            .andExpect(jsonPath("$[0].titulo").value(livroAux.titulo))
            .andExpect(jsonPath("$[0].descricao").value(livroAux.descricao))
    }

    @Test
    fun `listar todos erro, vazio`(){
        repository.deleteAll()
        mvc.perform(get("/livraria/listarTodosLivros"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `listar livro id`(){
        var livroAux  = Livro(1L, "teste titulo", "Teste descricao", null)
        repository.save(livroAux)

        mvc.perform(get("/livraria/listarLivro/{id}", 1))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(livroAux.id))
            .andExpect(jsonPath("$.titulo").value(livroAux.titulo))
            .andExpect(jsonPath("$.descricao").value(livroAux.descricao))
    }
    @Test
    fun `listar livro id INCORRETO`(){
        var livroAux  = Livro(1L, "teste titulo", "Teste descricao", null)
        mvc.perform(get("/livraria/listarLivro/{id}", 77))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deletar todos com sucesso`(){
        var livroAux  = Livro(1L, "teste titulo", "Teste descricao", null)
        var livroAux2  = Livro(2L, "teste titulo 2", "Teste descricao 2", null)
        repository.saveAll(listOf(livroAux2, livroAux))

        mvc.perform(delete("/livraria/deletarTodos"))
            .andExpect(status().isOk)

        val livros = repository.findAll()
        assertThat(livros).isEmpty()


    }
//    @Test
//    fun `deletar todos com erro, estando vazio`() {
//        `when`(repository.findAll()).thenReturn(emptyList())
//        mvc.perform(delete("/livraria/deletarTodos")
////            .contentType(PageAttributes.MediaType.APPLICATION_JSON_VALUE))
////            .andExpect(status().isNotFound)
//
//    }



}