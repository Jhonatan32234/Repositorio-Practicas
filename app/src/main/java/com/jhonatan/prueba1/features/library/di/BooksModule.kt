package com.jhonatan.prueba1.features.library.di

import com.jhonatan.prueba1.core.di.AppContainer
import com.jhonatan.prueba1.features.library.domain.usecases.GetBooksUseCase
import com.jhonatan.prueba1.features.library.presentation.viewmodel.BooksViewModelFactory

class BooksModule(private val appContainer: AppContainer) {

    private fun provideGetBooksUseCase(): GetBooksUseCase {
        return GetBooksUseCase(appContainer.booksRepository)
    }

    fun provideBooksViewModelFactory(): BooksViewModelFactory {
        return BooksViewModelFactory(
            getBooksUseCase = provideGetBooksUseCase()
        )
    }
}