package com.peep.nocalorieleftbehind.preference.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldLabelPosition
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peep.nocalorieleftbehind.core.domain.model.Nutrient
import com.peep.nocalorieleftbehind.core.di.CoreModule
import com.peep.nocalorieleftbehind.core.ui.theme.NoCalorieLeftBehindTheme
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.preference.di.PreferenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PreferenceScreen(onContinue: () -> Unit) {
    val viewModel = koinViewModel<PreferenceViewModel>()
    val preferenceUiState = viewModel.preferenceUiState.collectAsStateWithLifecycle()

    SuccessfulUI(
        preferenceUiState = { preferenceUiState.value },
        onInput = viewModel::onInput,
        onSave = { viewModel.savePreference(onCompletion = onContinue) },
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SuccessfulUI(
    preferenceUiState: () -> PreferenceUiState,
    onInput: (NutrientInput) -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                FilledTonalIconButton(
                    modifier =
                        Modifier
                            .padding(horizontal = 8.dp)
                            .size(
                                IconButtonDefaults.largeContainerSize(
                                    IconButtonDefaults.IconButtonWidthOption.Wide
                                )
                            ),
                    onClick = onSave,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(IconButtonDefaults.largeIconSize)
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item("title") {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    text = "Set your intake goals",
                    style = MaterialTheme.typography.displaySmall
                )
            }

            items(
                items = Nutrient.entries,
                key = { it.name },
            ) { nutrient ->

                val nutrientUiState = preferenceUiState().getNutrient(nutrient)
                val textFieldState = rememberTextFieldState(
                    initialText = (nutrientUiState as? UiState.Success<Int>)?.data?.toString() ?: ""
                )

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
                        enabled = nutrientUiState !is UiState.Loading,
                        labelPosition = TextFieldLabelPosition.Above(),
                        label = {
                            if (nutrient != Nutrient.CALORIES) {
                                Text(
                                    text = "(Optional)",
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        },
                        textStyle = MaterialTheme.typography.titleLargeEmphasized.copy(textAlign = TextAlign.Center),
                        state = textFieldState,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        isError = nutrientUiState is UiState.Error,
                        supportingText = {
                            if (nutrientUiState is UiState.Error) {
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
                        onInput(
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

@Preview
@Composable
private fun PreviewSuccessfulUi() {
    val context = LocalContext.current
    KoinApplication(
        application = {
            androidContext(context)
            modules(PreferenceModule, CoreModule)
        }
    ) {
        NoCalorieLeftBehindTheme {
            SuccessfulUI(
                preferenceUiState = { PreferenceUiState.default() },
                onInput = {},
                onSave = {}
            )
        }
    }
}