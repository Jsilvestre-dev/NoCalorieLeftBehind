package com.peep.nocalorieleftbehind.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.peep.nocalorieleftbehind.core.ui.theme.NoCalorieLeftBehindTheme

@Composable
fun DashboardScreen() {
    Success()
}

@Composable
private fun Success() {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Composable
@Preview
private fun Private() {
    NoCalorieLeftBehindTheme {
        DashboardScreen()
    }
}