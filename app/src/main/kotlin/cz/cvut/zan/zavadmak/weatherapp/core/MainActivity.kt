package cz.cvut.zan.zavadmak.weatherapp.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.AppRouter
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                AppRouter()
            }
        }
    }
}