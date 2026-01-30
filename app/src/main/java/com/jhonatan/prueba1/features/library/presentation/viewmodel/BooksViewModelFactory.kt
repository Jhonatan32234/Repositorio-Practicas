package com.jhonatan.prueba1.features.library.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhonatan.prueba1.features.library.domain.usecases.GetBooksUseCase

class BooksViewModelFactory(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksViewModel(getBooksUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}