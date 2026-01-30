package com.jhonatan.prueba1.features.library.domain.usecases

import com.jhonatan.prueba1.features.library.domain.repositories.BooksRepository
import com.jhonatan.prueba1.features.library.domain.entities.Book

class GetBooksUseCase(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(): Result<List<Book>> {
        return try {
            val books = repository.getBooks()
            if (books.isEmpty()) {
                Result.failure(Exception("No books found"))
            } else {
                Result.success(books)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}