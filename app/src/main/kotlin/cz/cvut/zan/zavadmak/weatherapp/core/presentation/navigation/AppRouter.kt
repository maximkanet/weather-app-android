package cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.cvut.zan.zavadmak.weatherapp.home.domain.model.LocationRequestStatus
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.HomeScreen
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.viewmodel.HomeViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.SearchScreen
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.SearchViewModel
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.SettingsScreen
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.viewmodel.SettingsViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation.WeatherScreens
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation.weatherNavGraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppRouter() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreens.Home
    ) {
        composable<MainScreens.Home> {

            val viewModel = koinViewModel<HomeViewModel>()

            val locationRequest by viewModel.locationRequest.collectAsStateWithLifecycle()
            val lastLocations by viewModel.lastLocations.collectAsStateWithLifecycle()
            val lastLocation by viewModel.lastLocation.collectAsStateWithLifecycle()

            LaunchedEffect(locationRequest) {
                if (locationRequest.status == LocationRequestStatus.SUCCESS) {
                    navController.navigate(
                        WeatherScreens.CurrentWeather(
                            longitude = lastLocation!!.longitude,
                            latitude = lastLocation!!.latitude
                        )
                    )
                }
            }

            HomeScreen(
                onSettingsButtonClick = {
                    navController.navigate(MainScreens.Settings)
                },
                onLocationClick = {
                    navController.navigate(
                        WeatherScreens.CurrentWeather(
                            latitude = it.latitude,
                            longitude = it.longitude
                        )
                    )
                },
                lastLocations = lastLocations,
                onSearchButtonClick = {
                    navController.navigate(MainScreens.Search)
                },
                onGetLocationClick = {
                    viewModel.getLastLocation()
                },
                locationRequestState = locationRequest
            )
        }

        composable<MainScreens.Settings> {
            val viewModel = koinViewModel<SettingsViewModel>()

            val units by viewModel.units.collectAsStateWithLifecycle()
            val notifications by viewModel.notifications.collectAsStateWithLifecycle()

            SettingsScreen(
                onCloseClick = {
                    navController.popBackStack()
                },
                units = units,
                onUnitChange = { unit ->
                    viewModel.changeUnit(unit)
                },
                onNotificationToggle = { type, checked ->
                    viewModel.changeNotificationState(type, checked)
                },
                notifications = notifications,
            )
        }

        composable<MainScreens.Search> {

            val viewModel = koinViewModel<SearchViewModel>()

            val resultList by viewModel.result.collectAsStateWithLifecycle()

            SearchScreen(
                result = resultList,
                onSearch = { query ->
                    viewModel.search(query)
                },
                onLocationClick = {
                    navController.navigate(
                        WeatherScreens.CurrentWeather(
                            longitude = it.longitude,
                            latitude = it.latitude
                        )
                    ) {
                        popUpTo(navController.currentDestination?.route ?: "") {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        weatherNavGraph(
            navController = navController,
            startDestination = WeatherScreens.CurrentWeather(
                latitude = 50.073658,
                longitude = 14.418540
            )
        )
    }
}