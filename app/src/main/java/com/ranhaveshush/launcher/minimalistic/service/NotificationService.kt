package com.ranhaveshush.launcher.minimalistic.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationDataSource
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NotificationService : NotificationListenerService() {
    private lateinit var serviceScope: CoroutineScope

    private lateinit var dataSource: NotificationDataSource

    override fun onListenerConnected() {
        serviceScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
        dataSource = InjectorUtils.provideNotificationDataSource()
    }

    override fun onListenerDisconnected() {
        if (this::serviceScope.isInitialized) {
            serviceScope.launch {
                dataSource.clear()
            }
            serviceScope.cancel()
        }
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.let {
            serviceScope.launch {
                dataSource.add(sbn)
                cancelNotification(sbn.key)
            }
        }
    }
}
