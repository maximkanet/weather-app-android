package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.MainScreens
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.CurrentWeatherWrapperScreen
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.ForecastScreen
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.CurrentWeatherViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.ForecastViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.weatherNavGraph(
    navController: NavController,
    startDestination: Any
) {
    navigation<MainScreens.Weather>(startDestination = startDestination) {
        composable<WeatherScreens.CurrentWeather> { backStack ->

            val route = backStack.toRoute<WeatherScreens.CurrentWeather>()

            val viewModel = koinViewModel<CurrentWeatherViewModel> {
                parametersOf(route.id)
            }

            val location by viewModel.location.collectAsStateWithLifecycle()
            val currentWeather by viewModel.weather.collectAsStateWithLifecycle()
            val daily by viewModel.daily.collectAsStateWithLifecycle()
            val hourly by viewModel.hourly.collectAsStateWithLifecycle()

            LaunchedEffect(location) {
                location?.let {
                    viewModel.fetchAll(
                        longitude = it.longitude,
                        latitude = it.latitude
                    )
                }
            }

            CurrentWeatherWrapperScreen(
                onHomeClick = { navController.navigateUp() },
                onSettingsClick = { navController.navigate(MainScreens.Settings) },
                location = location,
                currentWeather = currentWeather,
                daily = daily,
                onDetailedForecastClick = { navController.navigate(WeatherScreens.Forecast(id = it.id)) },
                hourly = hourly,
            )
        }

        composable<WeatherScreens.Forecast> { backStack ->

            val route = backStack.toRoute<WeatherScreens.Forecast>()

            val viewModel = koinViewModel<ForecastViewModel>()

            val forecast by viewModel.forecast.collectAsStateWithLifecycle()
            val forecastRange by viewModel.forecastRange.collectAsStateWithLifecycle()
            val location by viewModel.location.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.fetchLocation(id = route.id)
            }

            LaunchedEffect(location) {
                if (location != null) {
                    viewModel.fetchForecast(
                        longitude = location!!.longitude,
                        latitude = location!!.latitude,
                        range = forecastRange
                    )
                }
            }

            ForecastScreen(
                forecastRange = forecastRange,
                forecast = forecast,
                onDaysChange = {
                    viewModel.changeRange(it)
                    viewModel.fetchForecast(
                        longitude = location!!.longitude,
                        latitude = location!!.latitude,
                        range = it
                    )
                },
                onBackBack = { navController.popBackStack() },
                location = location,
            )
        }
    }
}