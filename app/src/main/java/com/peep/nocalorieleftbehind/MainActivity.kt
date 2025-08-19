package com.peep.nocalorieleftbehind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peep.nocalorieleftbehind.log_food.di.LogFoodModule
import com.peep.nocalorieleftbehind.log_food.ui.RecordIntakeScreen
import com.peep.nocalorieleftbehind.core.di.CoreModule
import com.peep.nocalorieleftbehind.core.ui.LogFoodScreen
import com.peep.nocalorieleftbehind.core.ui.SummaryScreen
import com.peep.nocalorieleftbehind.core.ui.PreferenceScreen
import com.peep.nocalorieleftbehind.core.ui.theme.NoCalorieLeftBehindTheme
import com.peep.nocalorieleftbehind.summary.di.SummaryModule
import com.peep.nocalorieleftbehind.summary.ui.DashboardScreen
import com.peep.nocalorieleftbehind.preference.di.PreferenceModule
import com.peep.nocalorieleftbehind.preference.ui.PreferenceScreen
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            KoinApplication(
                application = {
                    androidContext(this@MainActivity)
                    modules(
                        CoreModule,
                        SummaryModule,
                        PreferenceModule,
                        LogFoodModule,
                    )
                }
            ) {
                NoCalorieLeftBehindTheme {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = PreferenceScreen
                    ) {
                        composable<PreferenceScreen> {
                            PreferenceScreen(
                                onContinue = { navController.navigate(route = SummaryScreen) }
                            )
                        }
                        composable<SummaryScreen> {
                            DashboardScreen(
                                onNavigateToPreference = { navController.navigate(PreferenceScreen) }
                            )
                        }
                        composable<LogFoodScreen> {
                            RecordIntakeScreen(
                                onFinishedScreen = {
                                    navController.popBackStack(route = SummaryScreen, inclusive = false)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
