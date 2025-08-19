package com.peep.nocalorieleftbehind.log_food.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peep.nocalorieleftbehind.R
import com.peep.nocalorieleftbehind.core.di.CoreModule
import com.peep.nocalorieleftbehind.core.ui.theme.NoCalorieLeftBehindTheme
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.log_food.di.LogFoodModule
import com.peep.nocalorieleftbehind.preference.ui.NutrientInput
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun RecordIntakeScreen(
    onFinishedScreen: () -> Unit
) {

    val viewModel = koinViewModel<LogFoodViewModel>()
    val nutrients = viewModel.trackedNutrients.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val nutrientState = { uiState.value.foodNutrients }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onFinishedScreen,
                        colors = IconButtonDefaults.iconButtonVibrantColors()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                },
                title = {
                    Text(
                        text = "Adding Food Consumed"
                    )
                }
            )
        },
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
                    onClick = { viewModel.recordFoodConsumed(onFinishedScreen) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Done,
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item(
                key = "food_name"
            ) {

                val textFieldState = rememberTextFieldState()
                val focusedState = remember { mutableStateOf(false) }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged {
                            focusedState.value = it.isFocused
                        },
                    state = textFieldState,
                    labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                    label = {
                        if (textFieldState.text.isNotBlank()) {
                            Text(
                                text = stringResource(R.string.food_name)
                            )
                        }
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.food_name)
                        )
                    },
                    shape = ShapeDefaults.Large,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    onKeyboardAction = {
                        focusManager.clearFocus()
                    },
                    isError = uiState.value.foodName !is UiState.Success,
                    trailingIcon = {
                        if (focusedState.value) {
                            FilledTonalIconButton(
                                modifier = Modifier.size(IconButtonDefaults.extraSmallContainerSize()),
                                onClick = textFieldState::clearText
                            ) {
                                Icon(
                                    modifier = Modifier.size(IconButtonDefaults.extraSmallIconSize),
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = stringResource(R.string.clear_text),
                                )
                            }
                        }
                    }
                )
                textFieldState.text.toString().let { foodName ->
                    LaunchedEffect(foodName) {
                        viewModel.onNameChange(foodName)
                    }
                }
            }

            items(
                items = nutrients.value,
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
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    OutlinedTextField(
                        modifier = Modifier.weight(.5f),
                        state = textFieldState,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        onKeyboardAction = {
                            focusManager.clearFocus()
                        },
                        isError = nutrientState().get(nutrient) is UiState.Error,
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
            modules(LogFoodModule, CoreModule)
        }
    ) {
        NoCalorieLeftBehindTheme {
            RecordIntakeScreen(
                onFinishedScreen = {}
            )
        }
    }
}