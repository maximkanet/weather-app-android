package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType

interface SetNotificationStateUseCase {

    suspend fun execute(type: NotificationType, checked: Boolean)

}