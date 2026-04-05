package cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.cvut.zan.zavadmak.weatherapp.home.domain.model.LocationRequestStatus
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.HomeScreen
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.viewmodel.HomeViewModel
import cz.cvut.zan.zavadmak.weatherapp.search.domain.model.RequestState
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

            val initiator by viewModel.initiator.collectAsStateWithLifecycle()
            val mode by viewModel.mode.collectAsStateWithLifecycle()

            LaunchedEffect(locationRequest) {
                if (locationRequest.status == LocationRequestStatus.SUCCESS) {
                    lastLocation?.let {
                        navController.navigate(MainScreens.Weather(id = it.id)) {
                            launchSingleTop = true
                        }
                        viewModel.setRequestAsIdle()
                    }
                }
            }

            HomeScreen(
                onLocationClick = {
                    viewModel.markLocationAsUsed(id = it.id)
                    navController.navigate(MainScreens.Weather(id = it.id))
                },
                lastLocations = lastLocations,
                onSearchButtonClick = { navController.navigate(MainScreens.Search) },
                onGetLocationClick = { viewModel.getDeviceLastLocation() },
                onLocationsRemove = { viewModel.removeSelectedLocations() },
                locationRequestState = locationRequest,
                onLocationLongClick = { viewModel.switchModeToSelection(it) },
                mode = mode,
                initiatorId = initiator,
                onLocationChangeChecked = { id, checked ->
                    viewModel.processSelection(id, checked)
                },
            )
        }

        composable<MainScreens.Search> {
            val viewModel = koinViewModel<SearchViewModel>()

            val resultList by viewModel.result.collectAsStateWithLifecycle()
            val savedLocation by viewModel.savedLocation.collectAsStateWithLifecycle()
            val request by viewModel.requestState.collectAsStateWithLifecycle()

            LaunchedEffect(request) {
                if (request == RequestState.SUCCESS) {
                    savedLocation?.let {
                        navController.navigate(MainScreens.Weather(id = it.id)) {
                            popUpTo(MainScreens.Search) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                        viewModel.setRequestAsIdle()
                    }
                }
            }

            SearchScreen(
                result = resultList,
                onSearch = { viewModel.search(it) },
                onLocationClick = { viewModel.saveLocation(location = it) },
                onBack = { navController.popBackStack() }
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
                onNotificationsEnable = { viewModel.toggleNotifications(it) }
            )
        }

        weatherNavGraph(
            navController = navController,
            startDestination = WeatherScreens.CurrentWeather(id = 0)
        )
    }
}