package cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationDetailsViewModel(
    private val locationId: Int,
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase
) : ViewModel() {

    private val _location = MutableStateFlow<Location?>(null)
    val location = _location.asStateFlow()

    private fun applyDetails(location: Location?) {
        _location.update { location }
    }

    fun fetchLocation() {
        viewModelScope.launch {
            applyDetails(
                getLocationDetailsUseCase.execute(locationId)
            )
        }
    }


    init {
        fetchLocation()
    }

}