package com.peep.nocalorieleftbehind.record_intake

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peep.nocalorieleftbehind.core.data.local.IntakeRepository
import com.peep.nocalorieleftbehind.intake_preference.domain.ValidateNutrientValueUseCase
import com.peep.nocalorieleftbehind.intake_preference.ui.NutrientInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecordIntakeViewModel(
    intakeRepository: IntakeRepository,
    private val validateNutrientValueUseCase: ValidateNutrientValueUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            intakeRepository.getPreference()?.let { preference ->
                _uiState.update { recordIntakeUiState ->
                    recordIntakeUiState.copy(
                        nutrients = preference.getNutrientList()
                    )
                }
            }
        }
    }

    private val _uiState = MutableStateFlow<IntakeUiState>(IntakeUiState.default)
    val uiState = _uiState.asStateFlow()

    fun onInput(nutrientInput: NutrientInput) {
        viewModelScope.launch {
            val validatedNutrientValue = validateNutrientValueUseCase(nutrientInput.value)
            _uiState.update { intakeUiState ->
                val foodNutrients = intakeUiState.foodNutrients.toMutableMap()
                foodNutrients.put(key = nutrientInput.nutrient, value = validatedNutrientValue)
                intakeUiState.copy(
                    foodNutrients = foodNutrients
                )
            }
        }
    }

}