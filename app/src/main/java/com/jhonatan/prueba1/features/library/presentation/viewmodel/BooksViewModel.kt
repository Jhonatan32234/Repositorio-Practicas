package com.jhonatan.prueba1.features.library.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        loadBooks()
    }

    private fun loadBooks() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = getBooksUseCase()
            _uiState.update { state ->
                result.fold(
                    onSuccess = { list -> state.copy(isLoading = false, books = list) },
                    onFailure = { error -> state.copy(isLoading = false, error = error.message) }
                )
            }
        }
    }
}