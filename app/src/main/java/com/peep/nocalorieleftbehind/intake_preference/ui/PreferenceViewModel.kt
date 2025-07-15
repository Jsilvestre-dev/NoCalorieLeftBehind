package com.peep.nocalorieleftbehind.intake_preference.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peep.nocalorieleftbehind.core.data.local.IntakeRepository
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.core.data.model.MacroNutrient
import com.peep.nocalorieleftbehind.core.data.model.Preference
import com.peep.nocalorieleftbehind.intake_preference.domain.ValidatePreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreferenceViewModel(
    intakeRepository: IntakeRepository,
    validatePreferenceUseCase: ValidatePreferenceUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            intakeRepository.getPreference()?.also { preference ->
                val validatedPreferenceResult = validatePreferenceUseCase(preference)
                _uiState.update {
                    UiState.Success(
                        IntakePreferenceUi(
                            calories = preference.calories to validatedPreferenceResult[MacroNutrient.CALORIES],
                            protein = preference.protein to validatedPreferenceResult[MacroNutrient.PROTEIN],
                            carbs = preference.carbs to validatedPreferenceResult[MacroNutrient.CARBS],
                            fats = preference.fats to validatedPreferenceResult[MacroNutrient.FATS]
                        )
                    )
                }
            } ?: run {
                UiState.Success(
                    Preference.default()
                )
            }
        }
    }

    private val _uiState = MutableStateFlow<UiState<IntakePreferenceUi>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()
}