package com.jhonatan.prueba1.core.network

import com.jhonatan.prueba1.features.library.data.datasources.remote.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String = "golang"
    ): BooksResponse
}