package cz.cvut.zan.zavadmak.weatherapp.home.presentation.model

import androidx.annotation.StringRes
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.home.domain.model.LocationRequestStatus

sealed class LocationRequestUiState(
    @StringRes
    val message: Int,
    val status: LocationRequestStatus,
) {

    class Idle : LocationRequestUiState(
        message = R.string.get_my_location,
        status = LocationRequestStatus.IDLE
    )

    class Getting: LocationRequestUiState(
        message = R.string.getting_location,
        status = LocationRequestStatus.GETTING
    )

    class Error: LocationRequestUiState(
        message = R.string.error_during_getting_location,
        status = LocationRequestStatus.ERROR
    )

    class Success: LocationRequestUiState(
        message = R.string.location_received,
        status = LocationRequestStatus.SUCCESS
    )

}