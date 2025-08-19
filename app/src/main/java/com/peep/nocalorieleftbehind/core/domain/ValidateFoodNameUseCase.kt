package com.peep.nocalorieleftbehind.core.domain

import com.peep.nocalorieleftbehind.core.util.UiState

class ValidateFoodNameUseCase() {

    operator fun invoke(string: String): UiState<String> {
        return if (string.isBlank()) UiState.Error() else UiState.Success(string)
    }
}