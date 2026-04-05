package cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.BottomNavBar
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.viewmodel.NavViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.navigation.locationGraph
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation.weatherNavGraph
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppRouter() {
    val navController = rememberNavController()

//    val locationPermissionState = rememberMultiplePermissionsState(
//        listOf(
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        )
//    )

    val navViewModel = koinViewModel<NavViewModel>()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentScreen = navViewModel.currentScreen,
                onButtonClick = { screen ->
                    navViewModel.changeScreen(screen)
                    navController.navigate(screen)
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainScreens.Weather,
            modifier = Modifier.padding(innerPadding)
        ) {
            weatherNavGraph(
                navController = navController
            )

            locationGraph(
                navController = navController
            )

            composable<MainScreens.Settings> {
                Text("Settings")
            }
        }
    }
}