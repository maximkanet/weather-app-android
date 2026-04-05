package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class SearchLocationUseCaseImpl : SearchLocationUseCase {
    override suspend fun execute(locationName: String): List<Location> {
        return listOf()
    }
}