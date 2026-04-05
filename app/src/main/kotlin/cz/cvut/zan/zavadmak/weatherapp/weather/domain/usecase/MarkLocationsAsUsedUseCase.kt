package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

interface MarkLocationsAsUsedUseCase {

    suspend fun execute(id: Long)

}