package cz.cvut.zan.zavadmak.weatherapp.settings.domain.model

data class NotificationState(
    val type: NotificationType,
    val checked: Boolean
)
