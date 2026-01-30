package com.jhonatan.prueba1.features.library.data.datasources.remote.mapper


import com.jhonatan.prueba1.features.library.data.datasources.remote.model.BookDto
import com.jhonatan.prueba1.features.library.domain.entities.Book

fun BookDto.toDomain(): Book {
    return Book(
        id = this.id,
        title = this.title,
        author = this.authorNames?.firstOrNull() ?: "Unknown Author",
        imageUrl = if (this.coverId != null)
            "https://covers.openlibrary.org/b/id/${this.coverId}-M.jpg"
        else ""
    )
}