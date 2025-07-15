package com.peep.nocalorieleftbehind.core.util

sealed interface Result {
    object Successful : Result
    object Waiting : Result
    object Failure : Result
}

