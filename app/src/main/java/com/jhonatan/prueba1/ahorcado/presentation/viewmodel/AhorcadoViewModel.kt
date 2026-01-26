package com.jhonatan.prueba1.ahorcado.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class AhorcadoViewModel : ViewModel() {
    private val palabras = listOf(
        "PROGRAMACION", "KOTLIN", "COMPUTADORA", "ANDROID",
        "DESARROLLO", "FUNCION", "VARIABLE", "CLASE"
    )

    private var palabraSecreta by mutableStateOf("")
    var letrasAdivinadas by mutableStateOf(setOf<Char>())
    var intentosRestantes by mutableStateOf(6)
    var estadoJuego by mutableStateOf("JUGANDO")
    var letrasUsadas by mutableStateOf("")

    init {
        nuevaPalabra()
    }

    private fun nuevaPalabra() {
        palabraSecreta = palabras[Random.nextInt(palabras.size)]
        letrasAdivinadas = setOf()
        intentosRestantes = 6
        estadoJuego = "JUGANDO"
        actualizarLetrasUsadas()
    }

    fun adivinarLetra(letra: Char) {
        if (estadoJuego != "JUGANDO") return

        val letraMayus = letra.uppercaseChar()

        if (letrasAdivinadas.contains(letraMayus)) return

        letrasAdivinadas = letrasAdivinadas + letraMayus
        actualizarLetrasUsadas()

        if (letraMayus !in palabraSecreta) {
            intentosRestantes--
            if (intentosRestantes <= 0) {
                estadoJuego = "PERDIDO"
            }
        } else if (palabraSecreta.all { it in letrasAdivinadas }) {
            estadoJuego = "GANADO"
        }
    }

    fun reiniciarJuego() {
        nuevaPalabra()
    }

    private fun actualizarLetrasUsadas() {
        letrasUsadas = letrasAdivinadas.sorted().joinToString(" ")
    }

    fun palabraMostrada(): String {
        return palabraSecreta.map {
            if (letrasAdivinadas.contains(it)) it else '_'
        }.joinToString(" ")
    }

    fun revelarPalabra(): String {
        return palabraSecreta
    }

    fun letraYaUsada(letra: String): Boolean {
        return letra in letrasUsadas
    }
}