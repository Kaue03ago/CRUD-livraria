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
        return (Livro(null, "", "",  emptyList(), 0.0))

    }

    fun createInstance(openLibraryResponse: OpenLibraryResponse): Livro {
        val livro = openLibraryResponse.docs.first()
        val autores = livro.author?.map { Autor(nome = it.toString()) } ?: emptyList()

        return this.createInstance().apply {
            titulo = livro.title.toString()
            descricao = livro.subtitle.toString()
            this.autores = autores
        }
    }
}