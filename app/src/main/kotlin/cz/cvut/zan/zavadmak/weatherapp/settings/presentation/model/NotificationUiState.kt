package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.model

import androidx.annotation.StringRes
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType

data class NotificationUiState(
    @StringRes
    val optionName: Int,
    @StringRes
    val optionDescription: Int?,
    val type: NotificationType,
    val checked: Boolean,
//    val enabled: Boolean
)
