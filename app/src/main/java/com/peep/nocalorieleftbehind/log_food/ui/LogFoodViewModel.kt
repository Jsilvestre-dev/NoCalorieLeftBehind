package com.peep.nocalorieleftbehind.log_food.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peep.nocalorieleftbehind.core.domain.model.Food
import com.peep.nocalorieleftbehind.core.domain.model.Nutrient
import com.peep.nocalorieleftbehind.core.domain.ValidateFoodNameUseCase
import com.peep.nocalorieleftbehind.core.domain.ValidateNutrientValueUseCase
import com.peep.nocalorieleftbehind.core.util.Result
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.log_food.data.LogFoodRepository
import com.peep.nocalorieleftbehind.preference.data.PreferenceRepository
import com.peep.nocalorieleftbehind.preference.ui.NutrientInput
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class LogFoodViewModel(
    private val logFoodRepository: LogFoodRepository,
    private val preferenceRepository: PreferenceRepository,
    private val validateNutrientValueUseCase: ValidateNutrientValueUseCase,
    private val validateFoodNameUseCase: ValidateFoodNameUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            preferenceRepository.getPreference()?.let { preference ->
                _trackedNutrients.update {
                    preference.getNutrientList()
                }
            }
        }
    }

    private val _trackedNutrients = MutableStateFlow<ImmutableList<Nutrient>>(persistentListOf(Nutrient.CALORIES))
    val trackedNutrients = _trackedNutrients.asStateFlow()

    private val _uiState = MutableStateFlow<FoodUiState>(FoodUiState.default)
    val uiState = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        viewModelScope.launch {
            _uiState.update { intakeUiState ->
                intakeUiState.copy(
                    foodName = validateFoodNameUseCase(name)
                )
            }
        }
    }

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

    @OptIn(ExperimentalTime::class)
    fun recordFoodConsumed(onCompletion: () -> Unit = {}) {
        viewModelScope.launch {
            val currentUiState = _uiState.value

            if (currentUiState.isFoodUiInvalid()) return@launch

            val food = currentUiState.let {
                Food(
                    name = (it.foodName as UiState.Success).data,
                    nutrients = it.toNutrientIntMap(),
                    timeStamp = Clock.System.now().toEpochMilliseconds()
                )
            }

            val saveResult = logFoodRepository.saveFood(food)

            if (saveResult != Result.Successful) return@launch

            onCompletion()
        }
    }

}