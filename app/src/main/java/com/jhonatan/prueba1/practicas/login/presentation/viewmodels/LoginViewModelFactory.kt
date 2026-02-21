package com.jhonatan.prueba1.practicas.login.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhonatan.prueba1.practicas.login.domain.usecase.LoginUseCase

class LoginViewModelFactory (
    private val loginUseCase : LoginUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginUseCase = loginUseCase) as T
    }
}

