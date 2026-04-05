package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class SearchForLocationUseCaseImpl : SearchForLocationUseCase {
    override suspend fun execute(locationName: String): List<Location> {
        return listOf(
            Location(
                id = 1,
                longitude = 10.4,
                latitude = 3.4,
                shortName = "Nova vodolaha",
                fullName = "Nova vodolaha"
            )
        )
    }
}