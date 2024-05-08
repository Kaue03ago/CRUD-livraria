package com.example.CROUD.factory

import com.example.CROUD.model.Autor
import com.example.CROUD.model.DTO.OpenLibrary.OpenLibraryResponse
import com.example.CROUD.model.Livro
import org.springframework.beans.factory.config.AbstractFactoryBean
import org.springframework.stereotype.Component

@Component
class LivroModelFactory : AbstractFactoryBean<Livro>() {
    override fun getObjectType(): Class<*> {
        return Livro::class.java
    }

    override fun createInstance(): Livro {
        return (Livro(null, "", 0.0,0,  0, "", emptyList(),emptyList() , emptyList(),emptyList(), 0.0, 0.0, 0.0, 0.0))

    }

    fun createInstance(openLibraryResponse: OpenLibraryResponse): Livro {
        val livro = openLibraryResponse.docs.first()
        val autores = livro.author?.map { Autor(nome = it.toString()) } ?: emptyList()

        return this.createInstance().apply {
            titulo = livro.title.toString()

            val descricaoCompleta = livro.subtitle.toString().take(255)?: ""
            if (descricaoCompleta.length>= 255){
                descricao = "${descricaoCompleta.take(252)}..."
            }else
                descricao = descricaoCompleta


           val generoC =livro.subject!!.take(10)
           if (generoC.size >= 10) {
                generos = livro.subject!!.take(10)
            } else {
               generos =livro.subject
            }

            this.autores = autores
            colaboradores = livro.contributor
            linguagens= livro.language
            anoPublicacao = livro.firstPublishYear
            paginas = livro.publishDate
        }
    }
}