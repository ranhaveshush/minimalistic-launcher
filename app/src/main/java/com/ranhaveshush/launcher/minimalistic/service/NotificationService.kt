package com.ranhaveshush.launcher.minimalistic.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NotificationService : NotificationListenerService() {
    private lateinit var serviceScope: CoroutineScope

    private lateinit var repository: NotificationRepository

    override fun onListenerConnected() {
        serviceScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
        repository = InjectorUtils.provideNotificationRepository(applicationContext)
    }

    override fun onListenerDisconnected() {
        if (this::serviceScope.isInitialized) {
            serviceScope.launch {
                repository.clear()
            }
            serviceScope.cancel()
        }
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.let {
            serviceScope.launch {
                repository.add(sbn)
                cancelNotification(sbn.key)
            }
        }
    }
}