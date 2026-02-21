package com.jhonatan.prueba1.features.nummagico.domain.entities

sealed class NumResult {
    object TooHigh : NumResult()
    object TooLow : NumResult()
    object Correct : NumResult()
    object OutOfRange : NumResult()
    object NoMoreAttempts : NumResult()
}