package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface GetLocationUseCase {

    suspend fun execute(longitude: Double, latitude: Double): Location?

}