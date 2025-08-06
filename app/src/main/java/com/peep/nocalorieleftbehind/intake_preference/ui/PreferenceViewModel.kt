package com.peep.nocalorieleftbehind.intake_preference.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peep.nocalorieleftbehind.core.data.local.IntakeRepository
import com.peep.nocalorieleftbehind.core.data.model.Preference
import com.peep.nocalorieleftbehind.core.util.Result
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
                    validateNutrientValueUseCase(preference)
                }
            }
        }
    }

    private val _preferenceUiState = MutableStateFlow(PreferenceUiState.default())
    val preferenceUiState = _preferenceUiState.asStateFlow()

    fun onInput(nutrientInput: NutrientInput) {
        println("onInput: $nutrientInput")
        viewModelScope.launch {
            _preferenceUiState.update { preferenceUiState ->
                preferenceUiState.updateNutrient(
                    nutrient = nutrientInput.nutrient,
                    uiState = validateNutrientValueUseCase(nutrientAmount = nutrientInput.value),
                )
            }
        }
    }

    fun savePreference(onCompletion: () -> Unit = {}) {
        viewModelScope.launch {
            val currentPreferenceUiState = _preferenceUiState.value

            if (!currentPreferenceUiState.isAllValid()) return@launch

            val preference = currentPreferenceUiState.let {
                Preference(
                    calories = (it.calories as UiState.Success<Int>).data,
                    protein = (it.protein as UiState.Success<Int>).data,
                    carbs = (it.carbs as UiState.Success<Int>).data,
                    fats = (it.fats as UiState.Success<Int>).data
                )
            }

            val saveResult = intakeRepository.savePreference(preference)

            if (saveResult != Result.Successful) return@launch

            onCompletion()
        }
    }
}