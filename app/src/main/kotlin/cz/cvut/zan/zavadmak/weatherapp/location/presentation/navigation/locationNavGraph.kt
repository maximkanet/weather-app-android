package cz.cvut.zan.zavadmak.weatherapp.location.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.MainScreens
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.LocationDetailsViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.LocationsViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.locationGraph(
    navController: NavController
) {
    navigation<MainScreens.Locations>(
        startDestination = LocationScreens.Locations
    ) {

        composable<LocationScreens.Locations> {

            val viewModel = koinViewModel<LocationsViewModel>()

            val locations by viewModel.locations.collectAsStateWithLifecycle()

            // ...

        }

        composable<LocationScreens.LocationDetails> { backStack ->

            val locationId = backStack.toRoute<LocationScreens.LocationDetails>().id

            val viewModel = koinViewModel<LocationDetailsViewModel>{
                parametersOf(locationId)
            }

            val location by viewModel.location.collectAsStateWithLifecycle()

            // ...

        }
        composable<LocationScreens.Search> {

            val viewModel = koinViewModel<SearchViewModel>()

            val query by viewModel.query.collectAsStateWithLifecycle()
            val locations by viewModel.locations.collectAsStateWithLifecycle()

            // ...
        }

    }
}