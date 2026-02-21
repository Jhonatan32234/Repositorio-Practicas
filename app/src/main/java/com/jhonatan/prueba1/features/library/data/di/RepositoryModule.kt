package com.jhonatan.prueba1.features.library.data.di

import com.jhonatan.prueba1.features.library.data.repositories.BooksRepositoryImpl
import com.jhonatan.prueba1.features.library.domain.repositories.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun bindBookRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BooksRepository
}