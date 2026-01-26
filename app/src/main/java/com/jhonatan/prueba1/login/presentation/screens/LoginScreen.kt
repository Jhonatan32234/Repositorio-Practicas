package com.jhonatan.prueba1.login.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.lifecycle.viewmodel.compose.viewModel
import com.jhonatan.prueba1.R
import com.jhonatan.prueba1.login.domain.usecase.LoginUseCase
import com.jhonatan.prueba1.login.presentation.viewmodels.LoginViewModel
import com.jhonatan.prueba1.login.presentation.viewmodels.LoginViewModelFactory

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(
    factory = LoginViewModelFactory(LoginUseCase())
)) {
    val message by viewModel.message.collectAsStateWithLifecycle()
    val username by viewModel.username.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()


    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Image(modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo empresa")
        Spacer(modifier = Modifier.height(40.dp))
        TextField(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp),
            value = username,
            onValueChange = {viewModel.onUsernameChange(it)},
            label = {Text("Username")},
            placeholder = {Text("Username")},

            )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp),
            value = password,
            onValueChange = {viewModel.onPasswordChange(it)},
            placeholder = {Text("Password")}
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp),
            onClick = {viewModel.verifiLogin()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("Iniciar sesión")
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(message,
                fontSize = 50.sp,
                color = Color.Green
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}