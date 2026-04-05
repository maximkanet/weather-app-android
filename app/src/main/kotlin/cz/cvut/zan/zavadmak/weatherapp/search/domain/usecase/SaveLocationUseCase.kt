package cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface SaveLocationUseCase {

    suspend fun execute(location: Location): Location

}