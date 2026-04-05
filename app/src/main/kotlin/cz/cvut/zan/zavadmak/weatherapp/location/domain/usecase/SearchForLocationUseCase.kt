package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface SearchForLocationUseCase {

    suspend fun execute(locationName: String): List<Location>

}