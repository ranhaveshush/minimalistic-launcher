package com.ranhaveshush.launcher.minimalistic.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationService : NotificationListenerService() {
    @Inject
    lateinit var dataSource: NotificationDataSource

    private lateinit var serviceScope: CoroutineScope

    override fun onListenerConnected() {
        serviceScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
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
            if (shouldInclude(sbn)) {
                serviceScope.launch {
                    dataSource.add(sbn)
                    cancelNotification(sbn.key)
                }
            }
        }
    }

    private fun shouldInclude(sbn: StatusBarNotification): Boolean {
        return !sbn.isOngoing && sbn.isClearable
    }
}
