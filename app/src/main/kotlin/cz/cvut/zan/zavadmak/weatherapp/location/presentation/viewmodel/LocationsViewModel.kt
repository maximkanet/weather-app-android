package cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.AddLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.RemoveLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val addLocationUseCase: AddLocationUseCase,
    private val removeLocationUseCase: RemoveLocationUseCase
) : ViewModel() {

    private val _locations = MutableStateFlow<List<Location>>(listOf())
    val locations = _locations.asStateFlow()

    private fun applyLocations(locations: List<Location>) {
        _locations.update { locations }
    }

    private fun fetchLocations() {
        viewModelScope.launch {
            applyLocations(getLocationsUseCase.execute())
        }
    }

    fun removeLocation(location: Location) {
        viewModelScope.launch {
            removeLocationUseCase.execute(location.id)
            applyLocations(
                locations.value.filterNot {
                    it.latitude == location.latitude && it.longitude == location.longitude
                }
            )
        }
    }

    fun addLocation(location: Location) {
        viewModelScope.launch {
            addLocationUseCase.execute(location)
            applyLocations(locations.value.plus(location))
        }
    }

    init {
        fetchLocations()
        Log.d("LocationsViewModel", "Loading locations ...")
    }
}