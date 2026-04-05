package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepository
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType

class SetNotificationStateUseCaseImpl(
    private val repository: SettingsRepository
) : SetNotificationStateUseCase {
    override suspend fun execute(
        type: NotificationType,
        checked: Boolean
    ) {
        repository.setNotificationState(type, checked)
    }
}