package cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface GetLastLocationsUseCase {

    suspend fun execute(): List<Location>

}