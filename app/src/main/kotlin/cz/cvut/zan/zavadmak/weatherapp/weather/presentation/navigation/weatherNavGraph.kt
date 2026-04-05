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
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherRequest
import cz.cvut.zan.zavadmak.weatherapp.weather.mapper.toUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.CurrentWeatherWrapperScreen
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.ForecastScreen
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.CurrentWeatherViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.ForecastViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.weatherNavGraph(
    navController: NavController,
    startDestination: Any
) {
    navigation<MainScreens.Weather>(startDestination = startDestination) {
        composable<WeatherScreens.CurrentWeather> { backStack ->

            val route = backStack.toRoute<WeatherScreens.CurrentWeather>()

            val viewModel = koinViewModel<CurrentWeatherViewModel>()

            val location by viewModel.location.collectAsStateWithLifecycle()
            val currentWeather by viewModel.weather.collectAsStateWithLifecycle()
            val daily by viewModel.daily.collectAsStateWithLifecycle()
            val hourly by viewModel.hourly.collectAsStateWithLifecycle()
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.setRequestState(WeatherRequest.UPDATING)
                viewModel.fetchLocation(longitude = route.longitude, latitude = route.latitude)
                viewModel.fetchCurrent(longitude = route.longitude, latitude = route.latitude)
                viewModel.fetchDaily(longitude = route.longitude, latitude = route.latitude)
                viewModel.fetchHourly(longitude = route.longitude, latitude = route.latitude)
                viewModel.setRequestState(WeatherRequest.SUCCESS)
                delay(1000)
                viewModel.setRequestState(WeatherRequest.IDLE)
            }

            CurrentWeatherWrapperScreen(
                onHomeClick = {
                    navController.navigate(MainScreens.Home)
                },
                onSettingsClick = {
                    navController.navigate(MainScreens.Settings)
                },
                location = location,
                request = requestState.toUiState(),
                currentWeather = currentWeather,
                daily = daily,
                onDetailedForecastClick = {
                    navController.navigate(
                        WeatherScreens.Forecast(
                            longitude = it.longitude,
                            latitude = it.latitude
                        )
                    )
                },
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
                viewModel.fetchLocation(longitude = route.longitude, latitude = route.latitude)
                viewModel.fetchForecast(
                    longitude = route.longitude,
                    latitude = route.latitude,
                    range = forecastRange
                )
            }

            ForecastScreen(
                forecastRange = forecastRange,
                forecast = forecast,
                onDaysChange = { viewModel.changeRange(it) },
                onBackBack = { navController.popBackStack() },
                location = location,
            )
        }
    }
}