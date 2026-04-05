package cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface MainScreens {

    @Serializable
    data object Home: MainScreens

    @Serializable
    data object Weather : MainScreens

    @Serializable
    data object Search: MainScreens

    @Serializable
    data object Settings: MainScreens
}