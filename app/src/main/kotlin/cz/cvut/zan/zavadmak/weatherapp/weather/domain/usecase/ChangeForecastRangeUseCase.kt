package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

interface ChangeForecastRangeUseCase {

    suspend fun execute(range: Int)

}