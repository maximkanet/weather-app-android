package cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLastLocationsUseCaseImpl : GetLastLocationsUseCase {
    override suspend fun execute(): List<Location> {
        return listOf()
    }
}