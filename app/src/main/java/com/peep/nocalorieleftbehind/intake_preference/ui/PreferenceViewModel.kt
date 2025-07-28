package com.peep.nocalorieleftbehind.intake_preference.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peep.nocalorieleftbehind.core.data.local.IntakeRepository
import com.peep.nocalorieleftbehind.core.data.model.Preference
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.intake_preference.domain.ValidateNutrientLimitsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreferenceViewModel(
    private val intakeRepository: IntakeRepository,
    private val validateNutrientLimitsUseCase: ValidateNutrientLimitsUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            intakeRepository.getPreference()?.also { preference ->
                _preferenceUiState.update {
                    PreferenceUiState(
                        trackedNutrientLimits = validateNutrientLimitsUseCase(preference.trackedNutrientLimits)
                    )
                }
            }
        }
    }

    private val _preferenceUiState = MutableStateFlow<PreferenceUiState>(PreferenceUiState.default())
    val preferenceUiState = _preferenceUiState.asStateFlow()

    fun onInput(nutrientInput: NutrientInput) {
        val validatedNutrientValueResult = validateNutrientLimitsUseCase(value = nutrientInput.value)
        _preferenceUiState.update { preferenceUiState ->
            preferenceUiState.update(
                valueAndResult = validatedNutrientValueResult,
                nutrient = nutrientInput.nutrient
            )
        }
    }

    fun savePreference(onCompletion: () -> Unit = {}) {
        viewModelScope.launch {
            val currentPreferenceUiState = _preferenceUiState.value
            if (!currentPreferenceUiState.isAllValid()) return@launch

            currentPreferenceUiState.let {
                intakeRepository.savePreference(
                    Preference(
                        calories = it.calories.first,
                        protein = it.protein.first,
                        carbs = it.carbs.first,
                        fats = it.fats.first
                    )
                )
            }
        }.invokeOnCompletion { onCompletion() }
    }
}