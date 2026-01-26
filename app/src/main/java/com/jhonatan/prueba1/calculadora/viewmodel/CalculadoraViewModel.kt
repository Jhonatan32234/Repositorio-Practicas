package com.jhonatan.prueba1.calculadora.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class CalculadoraViewModel : ViewModel() {
    var operacionTexto by mutableStateOf("")
    var resultadoTexto by mutableStateOf("")

    private val CHARS = 20

    fun onNumeroClick(digito: String) {
        if (operacionTexto.length < CHARS) {
            operacionTexto += digito
        }
    }

    fun onOperadorClick(operator: String) {
        if (operacionTexto.isEmpty()) return
        
        // Evitar dos o mas operadores
        val lastChar = operacionTexto.last().toString()
        if (Regex("[+\\-*/x.]").matches(lastChar)) {
            operacionTexto = operacionTexto.dropLast(1) + operator
        } else if (operacionTexto.length < CHARS) {
            operacionTexto += operator
        }
    }

    fun onLimpiarClick() {
        operacionTexto = ""
        resultadoTexto = ""
    }

    fun onBorrarClick() {
        if (operacionTexto.isNotEmpty()) {
            operacionTexto = operacionTexto.dropLast(1)
        }
    }

    fun calcular() {
        if (operacionTexto.isBlank()) return

        try {
            val input = operacionTexto.replace("x", "*").replace(",", ".")
            val hayOperador = Regex("[+\\-*/]").find(input)
            
            if (hayOperador == null) {
                resultadoTexto = operacionTexto
                return
            }

            val operador = hayOperador.value
            val partes = input.split(operador)

            if (partes.size != 2 || partes[0].isBlank() || partes[1].isBlank()) {
                resultadoTexto = "Error"
                return
            }

            val num1 = BigDecimal(partes[0].trim())
            val num2 = BigDecimal(partes[1].trim())

            val resultado: BigDecimal = when (operador) {
                "+" -> num1.add(num2)
                "-" -> num1.subtract(num2)
                "*" -> num1.multiply(num2)
                "/" -> {
                    if (num2 == BigDecimal.ZERO) throw ArithmeticException("División por cero")
                    num1.divide(num2, 8, RoundingMode.HALF_UP)
                }
                else -> throw Exception()
            }

            resultadoTexto = resultado.stripTrailingZeros().toPlainString()

        } catch (e: Exception) {
            resultadoTexto = "Error"
        }
    }
}
