package com.peep.nocalorieleftbehind.summary.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peep.nocalorieleftbehind.R
import com.peep.nocalorieleftbehind.core.di.CoreModule
import com.peep.nocalorieleftbehind.core.domain.model.Nutrient
import com.peep.nocalorieleftbehind.core.ui.theme.NoCalorieLeftBehindTheme
import com.peep.nocalorieleftbehind.core.ui.theme.jostFamily
import com.peep.nocalorieleftbehind.core.ui.theme.remFamily
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.preference.di.PreferenceModule
import com.peep.nocalorieleftbehind.summary.di.SummaryModule
import kotlinx.collections.immutable.toImmutableList
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    onNavigateToPreference: () -> Unit
) {

    val viewModel = koinViewModel<SummaryViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (val currentState = uiState.value) {
        is UiState.Error -> {}
        UiState.Loading -> {}
        is UiState.Success<SummaryUi> -> {
            Ui(
                summaryUi = currentState.data
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun Ui(

    summaryUi: SummaryUi
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(key = "header") {
                Header()
            }

            item("protein") {
                FlowRow(
                    maxLines = 3
                ) {
                    summaryUi.nutrientSummaryList.forEach { nutrientSummary ->
                        SmallNutritionItem(
                            modifier = Modifier.weight(.33f),
                            titleResId = nutrientSummary.nutrient.resId,
                            vectorResId = nutrientSummary.nutrient.iconResId,
                            eatenValue = nutrientSummary.eaten.toString(),
                            leftValue = nutrientSummary.left.toString()
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.calories),
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = jostFamily,
            )

            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "1370",
                        style = MaterialTheme.typography.displayLarge,
                        fontFamily = remFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "130",
                        style = MaterialTheme.typography.displayLarge,
                        fontFamily = remFamily,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        text = "eaten",
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = jostFamily,
                    )

                    Text(
                        text = "left",
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = jostFamily,
                    )
                }

            }
        }


        CircularWavyProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(.7f)
                .aspectRatio(1f),
            progress = { .6f },
            stroke = Stroke(
                width =
                    with(LocalDensity.current) {
                        6.dp.toPx()
                    },
                cap = StrokeCap.Round,
            ),
            trackStroke = Stroke(
                width =
                    with(LocalDensity.current) {
                        6.dp.toPx()
                    },
                cap = StrokeCap.Round,
            ),
            wavelength = 32.dp
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SmallNutritionItem(
    modifier: Modifier = Modifier,
    titleResId: Int,
    vectorResId: Int?,
    eatenValue: String,
    leftValue: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = eatenValue,
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = remFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = leftValue,
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = remFamily,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(
                    text = "eaten",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = jostFamily,
                )

                Text(
                    text = "left",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = jostFamily,
                )
            }

        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            vectorResId?.let {
                Icon(
                    imageVector = ImageVector.vectorResource(it),
                    contentDescription = null
                )
            }
            Text(
                text = stringResource(titleResId),
                fontFamily = jostFamily,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@PreviewDynamicColors
private fun Private() {
    val context = LocalContext.current
    KoinApplication(
        application = {
            androidContext(context)
            modules(
                CoreModule,
                PreferenceModule,
                SummaryModule
            )
        }
    ) {
        NoCalorieLeftBehindTheme {
            Ui(
                summaryUi = SummaryUi(
                    calories = NutrientSummary(
                        nutrient = Nutrient.CALORIES,
                        eaten = 1000,
                        left = 500,
                        total = 1500
                    ),
                    nutrientSummaryList = buildList {
                        add(
                            NutrientSummary(
                                nutrient = Nutrient.PROTEIN,
                                eaten = 82,
                                left = 68,
                                total = 150
                            )
                        )
                        add(
                            NutrientSummary(
                                nutrient = Nutrient.CARBS,
                                eaten = 49,
                                left = 31,
                                total = 80
                            )
                        )
                        add(
                            NutrientSummary(
                                nutrient = Nutrient.FATS,
                                eaten = 37,
                                left = 13,
                                total = 50
                            )
                        )
                    }.toImmutableList(),
                    date = "Aug 18, 2025"
                )
            )
        }
    }
}