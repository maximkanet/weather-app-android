package cz.cvut.zan.zavadmak.weatherapp.location.presentation.navigation

import kotlinx.serialization.Serializable

interface LocationScreens {

    @Serializable
    data object Locations : LocationScreens

    @Serializable
    data class LocationDetails(val id: Int): LocationScreens

    @Serializable
    data object Search: LocationScreens

}