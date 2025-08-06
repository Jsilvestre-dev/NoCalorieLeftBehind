package com.peep.nocalorieleftbehind.record_intake

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peep.nocalorieleftbehind.core.di.DataModule
import com.peep.nocalorieleftbehind.core.ui.theme.NoCalorieLeftBehindTheme
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.intake_preference.ui.NutrientInput
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun RecordIntakeScreen() {

    val viewModel = koinViewModel<RecordIntakeViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val nutrientState = { uiState.value.foodNutrients }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "What food did you eat?"
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(
                items = uiState.value.nutrients,
                key = { it.name }
            ) { nutrient ->

                val textFieldState = rememberTextFieldState()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        modifier = Modifier.weight(.5f),
                        text = stringResource(nutrient.resId),
                        style = MaterialTheme.typography.titleLarge,
                    )

                    OutlinedTextField(
                        modifier = Modifier.weight(.5f),
                        enabled = nutrientState()?.get(nutrient) !is UiState.Loading,
                        textStyle = MaterialTheme.typography.titleLargeEmphasized.copy(textAlign = TextAlign.Center),
                        state = textFieldState,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        isError = nutrientState()?.get(nutrient) is UiState.Error,
                        supportingText = {
                            if (nutrientState()?.get(nutrient) is UiState.Error) {
                                Text(
                                    text = "Something is wrong",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        },
                        shape = ShapeDefaults.Large,
                        inputTransformation = InputTransformation.maxLength(4)
                    )
                }

                textFieldState.text.toString().let { text ->
                    LaunchedEffect(text) {
                        viewModel.onInput(
                            NutrientInput(
                                value = runCatching { text.toInt() }.getOrNull(),
                                nutrient = nutrient
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    val context = LocalContext.current
    KoinApplication(
        application = {
            androidContext(context)
            modules(RecordIntakeModule, DataModule)
        }
    ) {
        NoCalorieLeftBehindTheme {
            RecordIntakeScreen()
        }
    }
}