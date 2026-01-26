package com.jhonatan.prueba1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jhonatan.prueba1.ahorcado.presentation.screens.AhorcadoScreen
import com.jhonatan.prueba1.calculadora.screens.CalculadoraScreen
import com.jhonatan.prueba1.ui.theme.Prueba1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prueba1Theme() {
                AhorcadoScreen()
            }
        }
    }
}
