package com.ranhaveshush.launcher.minimalistic.data.notification

import android.service.notification.StatusBarNotification
import kotlinx.coroutines.flow.Flow

/**
 * A data source of [notifications][StatusBarNotification] posted to the Android OS by apps.
 */
interface NotificationDataSource {
    fun asFlow(): Flow<Collection<StatusBarNotification>>
    suspend fun add(sbn: StatusBarNotification)
    suspend fun remove(sbn: StatusBarNotification)
    suspend fun remove(key: String)
    suspend fun clear()
}

