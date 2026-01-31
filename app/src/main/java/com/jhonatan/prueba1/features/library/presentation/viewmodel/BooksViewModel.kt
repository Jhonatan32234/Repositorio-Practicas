package com.jhonatan.prueba1.features.library.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonatan.prueba1.features.library.domain.entities.Book
import com.jhonatan.prueba1.features.library.domain.usecases.GetBooksUseCase
import com.jhonatan.prueba1.features.library.presentation.screens.BooksUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BooksViewModel(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState = _uiState.asStateFlow()

    fun onSearch(query: String) {
        if (query.isBlank()) return

        _uiState.update { it.copy(isLoading = true, books = emptyList(), error = null) }

        viewModelScope.launch {
            try {
                val result = getBooksUseCase(query)
                result.fold(
                    onSuccess = { list -> _uiState.update { it.copy(isLoading = false, books = list) } },
                    onFailure = { err ->
                        println("ERROR_TECNICO: ${err.localizedMessage}")
                        _uiState.update { it.copy(isLoading = false, error = "Error en el servidor") }
                    }
                )
            } catch (e: Exception) {
                println("ERROR_CRITICO: ${e.message}")
                _uiState.update { it.copy(isLoading = false, error = "Sin conexión a internet") }
            }
        }
    }
}