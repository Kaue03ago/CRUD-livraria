package com.example.CROUD.service

import com.example.CROUD.model.Livro
import com.example.CROUD.repository.LivroRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


//@SpringBootTest

@ExtendWith(MockitoExtension::class)
class ServiceTests {


    @Mock//cria um objeto falso
    lateinit var livroRepository: LivroRepository//repository




    @InjectMocks//injeta o objeto falso no objeto real p/ ser usado nos tests
    lateinit var livroService: LivroService//service

    @Test
    fun `adicionar livro com sucesso`(){

        val livroAux = Livro(id = null, titulo = "velozes e furiosos", categoria = "ação")//criando um livro
        // atribuindo a val um livro com essas propriedades

        `when`(livroRepository.save(livroAux)).thenReturn(livroAux)
        // qnd o livro for salvo, retorna o livro salvo

        val livroSalvo = livroService.inserirLivro(livroAux)//salvando o livro
        // eh feito quase a msm coisa da linha 35 mas chama o metodo inserirLivro,
        // e simula o salvamento do livro

        assertEquals(livroAux, livroSalvo)//comparando o livro salvo com o livro criado

    }


    @Test
    fun `adicionar livro com titulo null`(){
        val livroAux = Livro(id = null, titulo = "", categoria ="testando titulo vazio")
        //passando arfumentos normal

        assertThrows<IllegalArgumentException> {livroService.inserirLivro(livroAux)}
        //com a val, verifica se eh lancada um exce
    }

    @Test
    fun `add titulo repetido`(){
        val livroAux = Livro(id = 100, titulo = "velozes e furiosos", categoria = "ação")

        `when`(livroRepository.existsByTitulo(livroAux.titulo)).thenReturn(true)
        //verifica se o titulo ja existe

        assertThrows<IllegalArgumentException> {livroService.inserirLivro(livroAux)}


    }

    @Test
    fun `add livro com titulo menor3`(){

        val livroAux = Livro(id= null, titulo = "Eu", categoria = "teste")

        assertThrows<IllegalArgumentException> {livroService.inserirLivro(livroAux)  }
    }

    @Test
    fun `add livro com titulo maior 75`(){

        val livroAux = Livro(id= null, titulo = "O cão pulou a cerca e encontrou um gato " +
                "preguiçoso dormindo ao sol. " +
                "Eles brincaram juntos por horas.", categoria = "teste")

        assertThrows<IllegalArgumentException> {livroService.inserirLivro(livroAux)  }
    }


    @Test
    fun`remover livro corretamente`(){
        val id = 100L
        `when`(livroRepository.existsById(id)).thenReturn(true)

//        assertThrows<IllegalArgumentException> {livroService.removerLivro(id)}
        livroService.removerLivro(id)

    }

    @Test
    fun `remover livro inexistente`(){
        val id = 100L

        `when`(livroRepository.existsById(id)).thenReturn(false)

        assertThrows<NoSuchElementException> {livroService.removerLivro(id)}  }

    @Test
    fun `remover todos os livros corretamente`(){
       val livroAux: Livro = Livro(id = 100, titulo = "remov", categoria = "remo")

        `when`(livroRepository.findAll()).thenReturn(listOf(livroAux))//pq o findAll retorna uma lista de livros? pq ele retorna todos os livros

        livroService.removerTodosLivros()
    }
    @Test
    fun `remover livros sem livros`(){
        `when`(livroRepository.findAll()).thenReturn(emptyList())//o findAll retorna uma lista de livros

        assertThrows<NoSuchElementException> {livroService.removerTodosLivros()}
    }

    @Test

    fun `listar tds livros corretamente`(){
        val livroAux: Livro = Livro(id = 100, titulo = "lista", categoria = "list")

        `when`(livroRepository.findAll()).thenReturn(listOf(livroAux))

        livroService.listarTodosLivros()
    }

    @Test
    fun `sem livros p listar `(){

        `when`(livroRepository.findAll()).thenReturn(emptyList())

        assertThrows<NoSuchElementException> {livroService.listarTodosLivros()  }

    }

    @Test
    fun`listar livro id`(){
        val id = 100L
        val livroAux: Livro = Livro(id = id, titulo = "teste id livro", categoria = "bbbb")

//        `when`(livroRepository.existsById(id)).thenReturn(true)
        `when`(livroRepository.findById(id)).thenReturn(Optional.of(livroAux))// procura aux livro

        val livroResultante = livroService.listarLivro(id)// testa a fun

        assertEquals(livroAux, livroResultante)//é o esperado?

    }

    @Test
    fun `livro n encontrado `(){
        val id = 100L

        //`when`(livroRepository.existsById(id)).thenReturn(false)

        assertThrows<NoSuchElementException> {livroService.listarLivro(id)  }

    }






//    @Test
//    fun `editar livro corretamente`(){
//        val id = 100L
//        val livroAux: Livro = Livro(id = id, titulo = "sem", categoria = "dddd")
//        val livroEditado: Livro = Livro(id = id, titulo = "sem", categoria = "aaase")
//
//
//
//      livroService.editarLivro(id, livroEditado)
//
//        when {
//            // título e categoria ==
//            (livroAux.titulo == livroEditado.titulo) && (livroAux.categoria == livroEditado.categoria) -> {
//            }
//                        //  título foi alterado
//            (livroAux.titulo != livroEditado.titulo) && (livroAux.categoria == livroEditado.categoria) -> {//
//                if (!livroRepository.existsByTitulo(livroEditado.titulo)) {
//                    livroAux.titulo = livroEditado.titulo
//                } else {
//                    throw IllegalArgumentException("Título repetido")
//                }
//            }
//            //Categoria foi alterada
//            (livroAux.titulo == livroEditado.titulo) && (livroAux.categoria != livroEditado.categoria) -> {
//                livroAux.categoria = livroEditado.categoria
//            }
//            //título e categoria foram alterados, usando flag pra facilitar -> tituloalterado
//            else -> {
//                var tituloAlterado = false
//                if (!livroRepository.existsByTitulo(livroEditado.titulo)) {
//                    livroAux.titulo = livroEditado.titulo
//                    tituloAlterado = true
//                } else {
//                    throw IllegalArgumentException("Título repetido")
//                }
//                if (tituloAlterado) {
//                    livroAux.categoria = livroEditado.categoria
//                }
//            }
//        }
//
//        livroRepository.save(livroAux)
//
//
//
//            println("\n\n\n\n\n\n")
//            println(livroAux.titulo)
//            println(livroAux.categoria)
//            //return livroAux
//
//
//
//
//    }
}