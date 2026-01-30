package com.jhonatan.prueba1.features.library.presentation.screens

import com.jhonatan.prueba1.features.library.domain.entities.Book

data class BooksUiState(
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val error: String? = null
)