package com.example.CROUD.controllers

import com.example.CROUD.model.Livro
import com.example.CROUD.service.LivroService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@WithMockUser




class ControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var livroService: LivroService

    @Test
    fun `inserir livro corretamente`(){
        val livroAux =Livro(id = 1L, titulo = "Livro de Teste", categoria = "Ficção")



        `when`(livroService.inserirLivro(livroAux)).thenReturn(livroAux)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/livraria/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"id": 1, "titulo": "Livro de Teste", "categoria": "Ficção"}""")
        )
            .andExpect (MockMvcResultMatchers.status().isCreated)

    }

//    fun creatLivro(id: Long = 1L,                         NAO FUNFOU
//                     titulo: String = "Teste titulo",
//                     categoria: String = "Teste categoria") =Livro( id =id, titulo = titulo, categoria)
//
}