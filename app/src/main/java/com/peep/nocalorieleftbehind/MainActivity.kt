package com.peep.nocalorieleftbehind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peep.nocalorieleftbehind.add_food.AddFoodModule
import com.peep.nocalorieleftbehind.add_food.AddFoodScreen
import com.peep.nocalorieleftbehind.core.di.DataModule
import com.peep.nocalorieleftbehind.core.ui.AddFoodScreen
import com.peep.nocalorieleftbehind.core.ui.DashboardScreen
import com.peep.nocalorieleftbehind.core.ui.PreferenceScreen
import com.peep.nocalorieleftbehind.core.ui.theme.NoCalorieLeftBehindTheme
import com.peep.nocalorieleftbehind.dashboard.DashboardModule
import com.peep.nocalorieleftbehind.dashboard.DashboardScreen
import com.peep.nocalorieleftbehind.intake_preference.di.PreferenceModule
import com.peep.nocalorieleftbehind.intake_preference.ui.PreferenceScreen
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinApplication(
                application = {
                    androidContext(this@MainActivity)
                    modules(
                        DataModule,
                        DashboardModule,
                        PreferenceModule,
                        AddFoodModule,
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
                                onContinue = { navController.navigate(route = DashboardScreen) }
                            )
                        }
                        composable<DashboardScreen> {
                            DashboardScreen()
                        }
                        composable<AddFoodScreen> {
                            AddFoodScreen()
                        }
                    }
                }
            }
        }
    }
}
