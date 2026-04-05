package cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _locations = MutableStateFlow<List<Location>>(listOf())
    val locations = _locations.asStateFlow()

    private fun applyLocations(locations: List<Location>) {
        _locations.update { locations }
    }

    private fun fetchLocations() {
        viewModelScope.launch {
            applyLocations(
                getLocationsUseCase.execute()
            )
        }
    }

    init {
        fetchLocations()
        Log.d("LocationsViewModel", "Loading locations ...")
    }
}