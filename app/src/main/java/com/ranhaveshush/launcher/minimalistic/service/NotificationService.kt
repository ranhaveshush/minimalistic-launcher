package com.ranhaveshush.launcher.minimalistic.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NotificationService : NotificationListenerService() {
    private val tag = "NotificationService"

    private lateinit var serviceScope: CoroutineScope

    private lateinit var repository: NotificationRepository

    private var isConnected: Boolean = false

    override fun onListenerConnected() {
        Log.d(tag, "onListenerConnected()")

        serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        repository = InjectorUtils.provideNotificationRepository(applicationContext)

        isConnected = true
    }

    override fun onListenerDisconnected() {
        Log.d(tag, "onListenerDisconnected()")

        if (this::serviceScope.isInitialized) {
            serviceScope.launch {
                repository.clear()
            }
            serviceScope.cancel()
        }

        isConnected = false
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        Log.d(tag, "onNotificationPosted($sbn)")

        sbn?.let {
            serviceScope.launch {
                repository.add(sbn)
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        Log.d(tag, "onNotificationRemoved($sbn)")

        sbn?.let {
            serviceScope.launch {
                repository.remove(sbn)
            }
        }
    }
}