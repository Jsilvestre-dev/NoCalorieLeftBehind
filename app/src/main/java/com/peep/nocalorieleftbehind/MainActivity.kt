package com.peep.nocalorieleftbehind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peep.nocalorieleftbehind.data.DataModule
import com.peep.nocalorieleftbehind.ui.theme.NoCalorieLeftBehindTheme
import com.peep.nocalorieleftbehind.welcome.SelectionScreen
import com.peep.nocalorieleftbehind.welcome.WelcomeModule
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
                        WelcomeModule,
                        DataModule
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
                            SelectionScreen()
                        }
                    }
                }
            }
        }
    }
}
