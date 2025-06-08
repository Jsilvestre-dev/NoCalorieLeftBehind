package com.peep.nocalorieleftbehind.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peep.nocalorieleftbehind.data.local.IntakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(
    val intakeRepository: IntakeRepository
) : ViewModel() {
    private val _intakeTarget = MutableStateFlow<IntakeTarget?>(null)
    val intakeTarget = _intakeTarget.asStateFlow()

    fun onContinue() {

    }

    fun onSkip() {

    }

    fun saveIntakeTarget() {
        viewModelScope.launch {
            val currentIntake = intakeTarget.value
            if (currentIntake == null) return@launch

            intakeRepository.saveIntakeTarget(currentIntake)
        }
    }
}