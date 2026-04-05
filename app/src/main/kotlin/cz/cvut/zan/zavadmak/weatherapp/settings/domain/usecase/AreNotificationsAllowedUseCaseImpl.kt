package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepository
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType

class AreNotificationsAllowedUseCaseImpl(
    private val repository: SettingsRepository
) : AreNotificationsAllowedUseCase {
    override suspend fun execute(): Boolean {
        return repository.getNotificationState(NotificationType.ALL).checked
    }
}