package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

interface RemoveLocationUseCase {

    suspend fun execute(locationId: Long)

}