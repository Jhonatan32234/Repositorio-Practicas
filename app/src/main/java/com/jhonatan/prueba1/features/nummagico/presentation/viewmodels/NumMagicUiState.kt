package com.jhonatan.prueba1.features.nummagico.presentation.viewmodels


data class NumMagicUiState(
    val minRangeInput: String = "1",
    val maxRangeInput: String = "100",
    val userNumInput: String = "",
    val maxAttemptsInput: String = "5",
    val targetNumber: Int? = null,
    val message: String = "Configura el rango y presiona Iniciar",
    val attemptsLeft: Int = 0,
    val isGameActive: Boolean = false,
    val isCorrect: Boolean = false,
    val isGameOver: Boolean = false
)