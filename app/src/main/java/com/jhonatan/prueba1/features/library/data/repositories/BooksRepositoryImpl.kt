package com.jhonatan.prueba1.features.library.data.repositories


import com.jhonatan.prueba1.features.library.data.datasources.remote.api.OpenLibraryApi
import com.jhonatan.prueba1.features.library.data.datasources.remote.mapper.toDomain
import com.jhonatan.prueba1.features.library.domain.entities.Book
import com.jhonatan.prueba1.features.library.domain.repositories.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: OpenLibraryApi
) : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> {
        val response = api.searchBooks(query)
        return response.docs.map { it.toDomain() }
    }
}