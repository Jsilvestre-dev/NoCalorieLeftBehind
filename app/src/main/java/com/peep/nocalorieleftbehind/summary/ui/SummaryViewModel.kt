package com.peep.nocalorieleftbehind.summary.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peep.nocalorieleftbehind.core.domain.model.Consumption
import com.peep.nocalorieleftbehind.core.domain.model.Preference
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.preference.data.PreferenceRepository
import com.peep.nocalorieleftbehind.summary.data.SummaryRepository
import com.peep.nocalorieleftbehind.summary.data.toSummaryUi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SummaryViewModel(
    private val summaryRepository: SummaryRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val summaryUi = runCatching {
                val consumption = async { summaryRepository.getMostRecentDailyIntakeSummary() }.await()
                val preference = async { preferenceRepository.getPreference() }.await() ?: return@runCatching null
                return@runCatching toSummaryUi(consumption = consumption, preference = preference)
            }.onFailure { exception ->
                Log.e("${exception.cause}", "${exception.message}")
                return@launch _uiState.update { UiState.Error("Try refreshing.") }
            }.getOrNull()
                ?: return@launch _uiState.update { UiState.Error("Check that preferences are set.") }


            _uiState.update {
                UiState.Success(
                    data = summaryUi
                )
            }
        }
    }

    private val _uiState = MutableStateFlow<UiState<SummaryUi>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

}