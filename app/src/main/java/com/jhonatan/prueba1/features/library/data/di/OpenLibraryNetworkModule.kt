package com.jhonatan.prueba1.features.library.data.di

import com.jhonatan.prueba1.core.di.OpenLibraryRetrofit
import com.jhonatan.prueba1.features.library.data.datasources.remote.api.OpenLibraryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenLibraryModule {
    @Provides
    @Singleton
    fun provideOpenLibraryApi(@OpenLibraryRetrofit retrofit: Retrofit): OpenLibraryApi {
        return retrofit.create(OpenLibraryApi::class.java)
    }
}