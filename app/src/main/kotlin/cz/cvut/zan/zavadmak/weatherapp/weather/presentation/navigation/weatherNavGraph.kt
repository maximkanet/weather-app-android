package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.MainScreens
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.sharedKoinViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.LocationsViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherUnits
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.CurrentWeatherScreen
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.ForecastScreen
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.CurrentWeatherViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.ForecastViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.weatherNavGraph(
    navController: NavController
) {
    // Get current location ...

    val location = Pair(10.0, 5.0)

    navigation<MainScreens.Weather>(
        startDestination = WeatherScreens.CurrentWeather(
            locationId = 1
        )
    ) {
        composable<WeatherScreens.CurrentWeather> { backStack ->

            val locationsViewModel = backStack.sharedKoinViewModel<LocationsViewModel>(
                navController = navController
            )

            val locationId = backStack.toRoute<WeatherScreens.CurrentWeather>().locationId

            val locations by locationsViewModel.locations.collectAsStateWithLifecycle()

            val currentLocation = locations.find { it.id == locationId }

            if(currentLocation == null) {
                Log.e("weatherNavGraph", "Location with $locationId id was not found")
            }

            val viewModel = koinViewModel<CurrentWeatherViewModel> {
                parametersOf(currentLocation?.longitude ?: 0.0, currentLocation?.latitude ?: 0.0)
            }

            val weather by viewModel.weather.collectAsStateWithLifecycle()
            val daily by viewModel.daily.collectAsStateWithLifecycle()

            CurrentWeatherScreen(
                weather = weather,
                location = currentLocation,
                dailyWeather = daily,
                onDetailedForecastClick = {
                    navController.navigate(WeatherScreens.Forecast(it.id))
                }
            )

        }

        composable<WeatherScreens.Forecast> { backStack ->

            val locationsViewModel = backStack.sharedKoinViewModel<LocationsViewModel>(
                navController = navController
            )

            val locationId = backStack.toRoute<WeatherScreens.CurrentWeather>().locationId

            val locations by locationsViewModel.locations.collectAsStateWithLifecycle()

            val currentLocation = locations.find { it.id == locationId }

            if(currentLocation == null) {
                Log.e("weatherNavGraph", "Location with $locationId id was not found")
            }

            val viewModel = koinViewModel<ForecastViewModel> {
                parametersOf(currentLocation?.longitude ?: 0.0, currentLocation?.latitude ?: 0.0)
            }

            val forecast by viewModel.forecast.collectAsStateWithLifecycle()

            ForecastScreen(
                weatherUnits = WeatherUnits.asDefault(),
                forecast = forecast
            )
        }
    }
}