package cz.cvut.zan.zavadmak.weatherapp.core.domain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationPublisher : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val helper = NotificationHelper(context)
        val type = intent.getIntExtra("type", Notifications.UNKNOWN)
        helper.notify(type)
    }

}