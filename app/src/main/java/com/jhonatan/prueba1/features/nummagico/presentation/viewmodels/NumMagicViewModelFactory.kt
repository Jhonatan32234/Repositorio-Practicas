package com.jhonatan.prueba1.features.nummagico.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhonatan.prueba1.features.nummagico.domain.usecases.NumMagicUseCase

class NumMagicViewModelFactory(
    private val useCase: NumMagicUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NumMagicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NumMagicViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}