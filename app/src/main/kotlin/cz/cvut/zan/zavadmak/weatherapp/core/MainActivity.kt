package cz.cvut.zan.zavadmak.weatherapp.core

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.AppRouter
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.WeatherAppTheme
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation.WeatherScreens

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                navController = rememberNavController()
                AppRouter(navController = navController)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val id = intent.extras?.getLong("") ?: return

        navController.navigate(WeatherScreens.CurrentWeather(id))
    }
}