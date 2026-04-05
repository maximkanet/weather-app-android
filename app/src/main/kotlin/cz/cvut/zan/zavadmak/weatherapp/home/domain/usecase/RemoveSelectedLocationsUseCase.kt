package cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase

interface RemoveSelectedLocationsUseCase {

    suspend fun execute(items: List<Long>)

}