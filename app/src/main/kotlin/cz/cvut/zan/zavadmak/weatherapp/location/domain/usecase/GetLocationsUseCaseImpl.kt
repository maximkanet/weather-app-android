package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLocationsUseCaseImpl : GetLocationsUseCase {
    override suspend fun execute(): List<Location> {
        return listOf(
            Location(
                id = 1,
                longitude = 10.0,
                latitude = 5.0,
                shortName = "Nova Vodolaha",
                fullName = "Nova Vodolaha, lal al lal al"
            )
        )
    }
}