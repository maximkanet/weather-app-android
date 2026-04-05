package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface GetLocationUseCase {

    suspend fun execute(id: Long): Location?

}