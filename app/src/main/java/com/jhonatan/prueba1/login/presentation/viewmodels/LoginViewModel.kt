package com.jhonatan.prueba1.login.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.jhonatan.prueba1.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(loginUseCase: LoginUseCase) : ViewModel() {
    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()


    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onPasswordChange(it: String) {
        _password.value = it
    }

    fun onUsernameChange(it: String) {
        _username.value = it
    }

    fun verifiLogin(){
        if(_username.value == "admin" && _password.value == "admin"){
            _message.value = "Login correcto"
        }else{
            _message.value = "Credenciales incorrectas"

        }
    }
}