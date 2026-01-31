package com.jhonatan.prueba1.features.library.domain.repositories

import com.jhonatan.prueba1.features.library.domain.entities.Book

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}