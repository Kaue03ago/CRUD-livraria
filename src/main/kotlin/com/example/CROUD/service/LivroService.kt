package com.example.CROUD.service

import com.example.CROUD.model.Livro
import com.example.CROUD.repository.LivroRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class LivroService(private val livroRepository: LivroRepository){

    fun inserirLivro(livro: Livro): Livro {

        if (livro.titulo.isBlank() || livro.categoria.isBlank()){
            throw IllegalArgumentException("Os campos não podem ser vazios")
        }
        //var livroMinusculo = livro.titulo.lowercase()
        livro.titulo = livro.titulo.lowercase()//transforma o titulo SEMPRE em minusculo

        if (livroRepository.existsByTitulo(livro.titulo)){
            throw IllegalArgumentException("Livro já cadastrado")
        }
        if (livro.titulo.length < 3){
            throw IllegalArgumentException("O título deve ter no mínimo 3 caracteres")
        }
        if(livro.titulo.length > 75){
            throw IllegalArgumentException("O título deve ter no máximo 75 caracteres")
        }
        return livroRepository.save(livro)//save é um método do JpaRepository q salva
        // o objeto no banco de dados
    }
    fun removerLivro(id: Long){

        if (!livroRepository.existsById(id)) {
            throw NoSuchElementException("Livro não encontrado")
        }

    return livroRepository.deleteById(id)
    }

    fun removerTodosLivros(){

        if (livroRepository.findAll().isEmpty()){//findall retorna uma lista de livros
            throw NoSuchElementException("Não há livros para deletar")
        }

        return livroRepository.deleteAll()
    }

    fun removerCategoria(categoria: String){
        return livroRepository.deleteByCategoria(categoria)
    }

    fun listarTodosLivros(): List<Livro>{

        if (livroRepository.findAll().isEmpty()){//findall retorna uma lista de livros
            throw NoSuchElementException("Não há livros para listar")
        }

        return livroRepository.findAll()
    }

    fun listarLivro(id: Long): Livro{
        return livroRepository.findById(id).orElseThrow { NoSuchElementException("Livro não encontrado") }
    }

    fun editarLivro(id: Long, livro: Livro): Livro? {

        val livroAux: Optional<Livro> = livroRepository.findById(id)
            if(livroAux.isEmpty){
                return null
            }
            var livroExistente = livroAux.get( )
            livroExistente.titulo = livroExistente.titulo.lowercase()

        if(livroRepository.existsByTitulo(livro.titulo)){
            throw IllegalArgumentException("Titulo repetido")
        }


        if (livro.titulo.isBlank() && livro.categoria.isBlank()){
            throw NoSuchElementException("Ambos os campos não podem ser vazios")
        }
        if(livro.titulo.isBlank()){
            livroExistente.categoria = livro.categoria
        }
        if(livro.categoria.isBlank()){
            livroExistente.titulo = livro.titulo

        }
//            livroExistente.titulo = livro.titulo
//            livroExistente.categoria = livro.categoria
            livroRepository.save(livroExistente)
            return livroExistente


//
//        return livroRepository.findById(id).map {
//            livroExistente -> livroExistente.titulo = livro.titulo
//            livroExistente.categoria = livro.categoria
//
//            livroRepository.save(livroExistente)
//        }.orElseGet {
//            null
//
//        }


    }
}