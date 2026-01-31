package com.jhonatan.prueba1.features.library.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("docs") val docs: List<BookDto>
)

data class BookDto(
    @SerializedName("key") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authorNames: List<String>?,
    @SerializedName("cover_i") val coverId: Int?,
)