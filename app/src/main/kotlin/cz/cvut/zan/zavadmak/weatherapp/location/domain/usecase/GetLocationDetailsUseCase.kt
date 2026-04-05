package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface GetLocationDetailsUseCase {

    suspend fun execute(locationId: Int): Location?

}