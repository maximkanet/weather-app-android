package cz.cvut.zan.zavadmak.weatherapp.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.LocationRequestUiState
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getLastLocationsUseCase: GetLastLocationsUseCase,
    private val getLastLocationUseCase: GetLastLocationUseCase
) : ViewModel() {

    private val _locationRequest = MutableStateFlow<LocationRequestUiState>(
        LocationRequestUiState.Idle()
    )
    val locationRequest = _locationRequest.asStateFlow()

    private val _lastLocation = MutableStateFlow<Location?>(null)
    val lastLocation = _lastLocation.asStateFlow()

    fun setRequestAsGetting() {
        _locationRequest.update {
            LocationRequestUiState.Getting()
        }
    }

    fun setRequestAsSuccess() {
        _locationRequest.update {
            LocationRequestUiState.Success()
        }
    }

    fun setRequestAsError() {
        _locationRequest.update {
            LocationRequestUiState.Error()
        }
    }

    fun getLastLocation() {
        setRequestAsGetting()
        viewModelScope.launch {
            val location = getLastLocationUseCase.execute()

            if (location == null) {
                setRequestAsError()
            } else {
                _lastLocation.update { location }
                setRequestAsSuccess()
            }
        }
    }

    private val _lastLocations = MutableStateFlow<List<Location>>(listOf())
    val lastLocations = _lastLocations.asStateFlow()

    fun applyLastLocations(items: List<Location>) {
        _lastLocations.update { items }
    }

    fun fetchLastLocations() {
        viewModelScope.launch {
            applyLastLocations(getLastLocationsUseCase.execute())
        }
    }

    init {
        fetchLastLocations()
    }

}