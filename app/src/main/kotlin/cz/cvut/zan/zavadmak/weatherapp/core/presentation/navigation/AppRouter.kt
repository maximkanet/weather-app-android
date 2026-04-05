package cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.HomeScreen
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.viewmodel.HomeViewModel
import cz.cvut.zan.zavadmak.weatherapp.search.presentation.screen.SearchScreen
import cz.cvut.zan.zavadmak.weatherapp.search.presentation.viewmodel.SearchViewModel
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.SettingsScreen
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.viewmodel.SettingsViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation.WeatherScreens
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation.weatherNavGraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppRouter(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = MainScreens.Home
    ) {
        composable<MainScreens.Home> {
            val viewModel = koinViewModel<HomeViewModel>()

            val locationRequest by viewModel.locationRequest.collectAsStateWithLifecycle()
            val lastLocations by viewModel.lastLocations.collectAsStateWithLifecycle()
            val lastLocation by viewModel.lastLocation.collectAsStateWithLifecycle()

            LaunchedEffect(lastLocation) {
                lastLocation?.let { navController.navigate(WeatherScreens.CurrentWeather(id = it.id)) }
            }

            HomeScreen(
                onSettingsButtonClick = { navController.navigate(MainScreens.Settings) },
                onLocationClick = {
                    viewModel.markLocationAsUsed(id = it.id)
                    navController.navigate(WeatherScreens.CurrentWeather(id = it.id))
                },
                lastLocations = lastLocations,
                onSearchButtonClick = { navController.navigate(MainScreens.Search) },
                onGetLocationClick = { viewModel.getDeviceLastLocation() },
                locationRequestState = locationRequest
            )
        }

        composable<MainScreens.Settings> {
            val viewModel = koinViewModel<SettingsViewModel>()

            val units by viewModel.units.collectAsStateWithLifecycle()
            val notificationsAllowed by viewModel.notificationsAllowed.collectAsStateWithLifecycle()
            val notifications by viewModel.notifications.collectAsStateWithLifecycle()

            SettingsScreen(
                onCloseClick = {
                    navController.popBackStack()
                },
                units = units,
                onUnitChange = { unit -> viewModel.changeUnit(unit) },
                onNotificationToggle = { type, checked ->
                    viewModel.changeNotificationState(type, checked)
                },
                notificationsAllowed = notificationsAllowed,
                notifications = notifications,
            )
        }

        composable<MainScreens.Search> {
            val viewModel = koinViewModel<SearchViewModel>()

            val resultList by viewModel.result.collectAsStateWithLifecycle()
            val savedLocation by viewModel.savedLocation.collectAsStateWithLifecycle()

            LaunchedEffect(savedLocation) {
                savedLocation?.let { navController.navigate(WeatherScreens.CurrentWeather(id = it.id)) }
            }

            SearchScreen(
                result = resultList,
                onSearch = { viewModel.search(it) },
                onLocationClick = { viewModel.saveLocation(location = it) },
                onBack = { navController.popBackStack() }
            )
        }

        weatherNavGraph(
            navController = navController,
            startDestination = WeatherScreens.CurrentWeather(
//                latitude = 50.073658,
//                longitude = 14.418540,
                id = 0
            )
        )
    }
}