package com.peep.nocalorieleftbehind.core.util

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String = "") : UiState<Nothing>
}