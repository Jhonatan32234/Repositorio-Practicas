package com.jhonatan.prueba1.core.di

import android.content.Context
import com.jhonatan.prueba1.core.network.OpenLibraryApi
import com.jhonatan.prueba1.features.library.domain.repositories.BooksRepository
import com.jhonatan.prueba1.features.library.data.repositories.BooksRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val openLibraryApi: OpenLibraryApi by lazy {
        retrofit.create(OpenLibraryApi::class.java)
    }

    val booksRepository: BooksRepository by lazy {
        BooksRepositoryImpl(openLibraryApi)
    }
}