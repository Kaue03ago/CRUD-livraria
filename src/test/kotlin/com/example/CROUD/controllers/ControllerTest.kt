package com.example.CROUD.controllers.LivroController

import com.example.CROUD.model.Livro
import com.example.CROUD.service.LivroService
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
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

//    @InjectMocks
//    private lateinit var livroController: LivroController

    @InjectMocks
    private var id : Long = 100L

    @InjectMocks
    private var titulo : String = "livro de teste"

    @InjectMocks
    private var categoria : String = "comedia"

    //@InjectMocks
   private lateinit var livroAux: Livro//esta eh a forma correta ?


    @Test
    fun `inserir livro corretamente`(){
         livroAux =Livro(id, titulo, categoria)



        `when`(livroService.inserirLivro(livroAux)).thenReturn(livroAux)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/livraria/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"id": $id, "titulo": "$titulo", "categoria": "$categoria"}""")

        )
            .andExpect (MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
            .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value(titulo))
            .andExpect(MockMvcResultMatchers.jsonPath("$.categoria").value(categoria))

    }

    @Test
    fun `inserir livro incorretamente `(){
        livroAux =Livro(id, titulo, categoria)


        `when`(livroService.inserirLivro(livroAux)).thenReturn(livroAux)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/livraria/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"id": $id, "titulo: "", categoria": "$categoria" }""")
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)//realmente deveria retornar bad request?

    }


}