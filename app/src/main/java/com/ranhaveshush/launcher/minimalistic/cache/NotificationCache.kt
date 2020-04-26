package com.ranhaveshush.launcher.minimalistic.cache

import android.service.notification.StatusBarNotification
import kotlinx.coroutines.flow.Flow

/**
 * A cache of [notifications][StatusBarNotification] posted to the Android OS by Apps.
 */
interface NotificationCache {
    fun asFlow(): Flow<Collection<StatusBarNotification>>
    fun getAll(): Collection<StatusBarNotification>
    suspend fun add(sbn: StatusBarNotification)
    suspend fun remove(sbn: StatusBarNotification)
    suspend fun remove(key: String)
    suspend fun clear()
}