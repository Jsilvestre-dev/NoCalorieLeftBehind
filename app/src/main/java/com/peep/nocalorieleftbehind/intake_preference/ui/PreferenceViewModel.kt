package com.peep.nocalorieleftbehind.intake_preference.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peep.nocalorieleftbehind.core.data.local.IntakeRepository
import com.peep.nocalorieleftbehind.core.data.model.Preference
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.intake_preference.domain.ValidateNutrientValueUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreferenceViewModel(
    private val intakeRepository: IntakeRepository,
    private val validateNutrientValueUseCase: ValidateNutrientValueUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            intakeRepository.getPreference()?.also { preference ->
                _preferenceUiState.update {
                    PreferenceUiState(
                        calories = validateNutrientValueUseCase(preference.trackedNutrientLimits),
                        protein = validateNutrientValueUseCase(preference.protein),
                        carbs = validateNutrientValueUseCase(preference.carbs),
                        fats = validateNutrientValueUseCase(preference.fats)
                    )
                }
            }
            _screenUiState.update { UiState.Success }
        }
    }

    private val _preferenceUiState = MutableStateFlow<PreferenceUiState>(PreferenceUiState.default())
    val preferenceUiState = _preferenceUiState.asStateFlow()

    private val _screenUiState = MutableStateFlow<UiState>(UiState.Loading)
    val screenUiState = _screenUiState.asStateFlow()

    fun onInput(nutrientInput: NutrientInput) {
        val validatedNutrientValueResult = validateNutrientValueUseCase(value = nutrientInput.value)
        _preferenceUiState.update { preferenceUiState ->
            if (_screenUiState.value !is UiState.Success) return

            preferenceUiState.update(
                valueAndResult = validatedNutrientValueResult,
                nutrient = nutrientInput.nutrient
            )
        }
    }

    fun savePreference(onCompletion: () -> Unit = {}) {
        viewModelScope.launch {
            val currentPreferenceUiState = _preferenceUiState.value
            if (_screenUiState.value !is UiState.Success) return@launch
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