package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepository
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationState
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType

class GetNotificationsUseCaseImpl(
    private val repository: SettingsRepository
) : GetNotificationsUseCase {
    override suspend fun execute(): List<NotificationState> {
        return listOf(
            repository.getNotificationState(NotificationType.ALL),
            repository.getNotificationState(NotificationType.MORNING_FORECAST),
        )
    }
}