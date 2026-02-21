package com.jhonatan.prueba1.features.nummagico.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.jhonatan.prueba1.features.nummagico.domain.entities.NumResult
import com.jhonatan.prueba1.features.nummagico.domain.usecases.NumMagicUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NumMagicViewModel(
    private val useCase: NumMagicUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NumMagicUiState())
    val uiState = _uiState.asStateFlow()

    fun onMinRangeChange(v: String) = _uiState.update { it.copy(minRangeInput = v) }
    fun onMaxRangeChange(v: String) = _uiState.update { it.copy(maxRangeInput = v) }
    fun onMaxAttemptsChange(v: String) = _uiState.update { it.copy(maxAttemptsInput = v) }
    fun onNumChange(v: String) = _uiState.update { it.copy(userNumInput = v) }

    fun startGame() {
        val min = _uiState.value.minRangeInput.toIntOrNull() ?: 1
        val max = _uiState.value.maxRangeInput.toIntOrNull() ?: 100
        val maxAtt = _uiState.value.maxAttemptsInput.toIntOrNull() ?: 5

        _uiState.update { it.copy(
            targetNumber = useCase.generateTargetNumber(min, max),
            attemptsLeft = maxAtt,
            isGameActive = true,
            isCorrect = false,
            isGameOver = false,
            message = "¡Juego iniciado! Tienes $maxAtt intentos.",
            userNumInput = ""
        ) }
    }

    fun checkNum() {
        val Num = _uiState.value.userNumInput.toIntOrNull() ?: return
        val target = _uiState.value.targetNumber ?: return
        val currentAttempts = _uiState.value.attemptsLeft

        val result = useCase.checkNum(Num, target)

        _uiState.update { state ->
            val nextAttempts = currentAttempts - 1

            when {
                result is NumResult.Correct -> state.copy(
                    isCorrect = true, isGameActive = false,
                    message = "¡Felicidades! El número era $target."
                )
                nextAttempts <= 0 -> state.copy(
                    isGameOver = true, isGameActive = false, attemptsLeft = 0,
                    message = "¡Perdiste! Se agotaron los intentos. El número era $target."
                )
                result is NumResult.TooHigh -> state.copy(
                    attemptsLeft = nextAttempts, message = "Muy alto. Te quedan $nextAttempts intentos."
                )
                result is NumResult.TooLow -> state.copy(
                    attemptsLeft = nextAttempts, message = "Muy bajo. Te quedan $nextAttempts intentos."
                )
                else -> state
            }
        }
    }
}