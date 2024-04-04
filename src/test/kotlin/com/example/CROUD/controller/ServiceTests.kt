package com.example.CROUD.controller

import com.example.CROUD.model.Livro
import com.example.CROUD.repository.LivroRepository
import com.example.CROUD.service.LivroService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension


//@SpringBootTest

@ExtendWith(MockitoExtension::class)
class ServiceTests {


    @Mock//cria um objeto falso
    lateinit var livroRepository: LivroRepository//repository




    @InjectMocks//injeta o objeto falso no objeto real p/ ser usado nos tests
    lateinit var livroService: LivroService//service

    @Test
    fun `adicionar livro com sucesso`(){

        val livro = Livro(id = null, titulo = "velozes e furiosos", categoria = "ação")//criando um livro


        `when`(livroRepository.save(livro)).thenReturn(livro)//quando o método save for chamado, ele vai retornar o livro
        val livroSalvo = livroService.inserirLivro(livro)//salvando o livro

        assertEquals(livro, livroSalvo)//comparando o livro salvo com o livro criado

    }








}