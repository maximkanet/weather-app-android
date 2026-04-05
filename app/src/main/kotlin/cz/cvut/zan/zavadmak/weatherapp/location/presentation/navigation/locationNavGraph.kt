package cz.cvut.zan.zavadmak.weatherapp.location.presentation.navigation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.MainScreens
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.LocationsScreen
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.SearchScreen
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.LocationsViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.locationGraph(
    navController: NavController
) {
    navigation<MainScreens.Locations>(
        startDestination = LocationScreens.Locations
    ) {
        composable<LocationScreens.Locations> {
            val viewModel = koinViewModel<LocationsViewModel>()

            val locations by viewModel.locations.collectAsStateWithLifecycle()

            LocationsScreen(
                locations = locations,
                onAddClick = {
                    navController.navigate(LocationScreens.Search)
                },
                onLocationRemove = { location ->
                    viewModel.removeLocation(location)
                }
            )
        }

        composable<LocationScreens.Search> {
            val locationsViewModel = koinViewModel<LocationsViewModel>()
            val viewModel = koinViewModel<SearchViewModel>()

            val query by viewModel.query.collectAsStateWithLifecycle()
            val locations by viewModel.locations.collectAsStateWithLifecycle()

            SearchScreen(
                result = locations,
                query = query,
                onSearch = {
                    viewModel.searchLocations(query)
                    Log.d("LocationNavGraph", "Searching ...")
                },
                onLocationClick = {
                    locationsViewModel.addLocation(it)
                    navController.navigate(LocationScreens.Locations)
                },
                onQueryChange = {
                    viewModel.applyQuery(it)
                }
            )
        }
    }
}