package com.ranhaveshush.launcher.minimalistic.cache

import android.service.notification.StatusBarNotification
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

/**
 * A [NotificationCache] implementation.
 */
class NotificationCacheImpl(
    private val cache: MutableMap<String, StatusBarNotification>,
    private val broadcastChannel: BroadcastChannel<Collection<StatusBarNotification>>
) : NotificationCache {
    override fun asFlow(): Flow<Collection<StatusBarNotification>> = broadcastChannel.asFlow()

    override fun getAll(): Collection<StatusBarNotification> = cache.values

    override suspend fun add(sbn: StatusBarNotification) {
        cache[sbn.key] = sbn
        updateChannel()
    }

    override suspend fun remove(sbn: StatusBarNotification) {
        remove(sbn.key)
    }

    override suspend fun remove(key: String) {
        cache.remove(key)
        updateChannel()
    }

    override suspend fun clear() {
        cache.clear()
        updateChannel()
    }

    private suspend fun updateChannel() {
        broadcastChannel.send(getAll())
    }
}