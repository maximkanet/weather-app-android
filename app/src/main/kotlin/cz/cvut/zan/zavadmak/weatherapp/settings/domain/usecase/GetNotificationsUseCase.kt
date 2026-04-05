package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationState

interface GetNotificationsUseCase {

    suspend fun execute(): List<NotificationState>

}