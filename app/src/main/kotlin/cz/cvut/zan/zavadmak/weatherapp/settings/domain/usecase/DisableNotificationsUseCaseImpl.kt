package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.core.domain.NotificationScheduler
import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepository
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType

class DisableNotificationsUseCaseImpl(
    private val repository: SettingsRepository,
    private val notificationScheduler: NotificationScheduler
) : DisableNotificationsUseCase {
    override suspend fun execute() {
        val items = listOf(
            repository.getNotificationState(NotificationType.MORNING_FORECAST)
        )

        for (item in items) {
            if(item.checked) {
                notificationScheduler.cancelNotification(item.type)
            }
        }
    }
}