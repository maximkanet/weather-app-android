package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

interface AreNotificationsAllowedUseCase {

    suspend fun execute() : Boolean

}