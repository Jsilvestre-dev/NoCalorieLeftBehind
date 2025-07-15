package com.peep.nocalorieleftbehind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peep.nocalorieleftbehind.core.ui.Dashboard
import com.peep.nocalorieleftbehind.core.ui.Welcome
import com.peep.nocalorieleftbehind.core.di.DataModule
import com.peep.nocalorieleftbehind.core.ui.theme.NoCalorieLeftBehindTheme
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
                        PreferenceModule
                    )
                }
            ) {
                NoCalorieLeftBehindTheme {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Welcome
                    ) {
                        composable<Welcome> {
                            PreferenceScreen(
                                onContinue = { navController.navigate(route = Dashboard) }
                            )
                        }
                        composable<Dashboard> {
                            Box(modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}
