package com.jhonatan.prueba1.features.nummagico.domain.usecases


import com.jhonatan.prueba1.features.nummagico.domain.entities.NumResult

class NumMagicUseCase {
    fun generateTargetNumber(min: Int, max: Int): Int {
        val rangeMin = if (min < max) min else max
        val rangeMax = if (max > min) max else min
        return (rangeMin..rangeMax).random()
    }

    fun checkNum(guess: Int, target: Int): NumResult {
        return when {
            guess > target -> NumResult.TooHigh
            guess < target -> NumResult.TooLow
            else -> NumResult.Correct
        }
    }
}