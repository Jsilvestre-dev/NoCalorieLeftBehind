package com.peep.nocalorieleftbehind.core.util

sealed interface UiState {
    object Loading : UiState
    object Success : UiState
    data class Error(val message: String) : UiState
}