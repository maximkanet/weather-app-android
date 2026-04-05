package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLocationDetailsUseCaseImpl : GetLocationDetailsUseCase {
    override suspend fun execute(locationId: Int): Location? {
        return null
    }
}