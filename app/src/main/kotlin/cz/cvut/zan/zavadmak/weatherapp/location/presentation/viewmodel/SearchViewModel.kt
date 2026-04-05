package cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.SearchForLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchForLocationUseCase: SearchForLocationUseCase
) : ViewModel() {

    private val _result = MutableStateFlow<List<Location>?>(null)
    val result = _result.asStateFlow()

    private fun applyLocations(locations: List<Location>) {
        _result.update { locations }
    }

    fun search(query: String) {
        viewModelScope.launch {
            applyLocations(searchForLocationUseCase.execute(query))
        }
    }

}