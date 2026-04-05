package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLocationsUseCaseImpl : GetLocationsUseCase {
    override suspend fun execute(): List<Location> {
        return listOf()
    }
}