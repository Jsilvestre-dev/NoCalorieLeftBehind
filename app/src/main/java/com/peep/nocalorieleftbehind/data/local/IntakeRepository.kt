package com.peep.nocalorieleftbehind.data.local

import com.peep.nocalorieleftbehind.welcome.IntakeTarget

interface IntakeRepository {
    suspend fun saveIntakeTarget(intakeTarget: IntakeTarget)
}