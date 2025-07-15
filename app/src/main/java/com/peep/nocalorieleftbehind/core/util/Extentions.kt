package com.peep.nocalorieleftbehind.core.util

fun Long.getRoomResult(): Result = if (this != -1L) Result.Successful else Result.Failure