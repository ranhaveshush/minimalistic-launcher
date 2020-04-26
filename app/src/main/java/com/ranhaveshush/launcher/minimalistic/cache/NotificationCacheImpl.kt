package com.ranhaveshush.launcher.minimalistic.cache

import android.service.notification.StatusBarNotification
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

/**
 * A [NotificationCache] implementation.
 */
class NotificationCacheImpl : NotificationCache {
    private val cache = HashMap<String, StatusBarNotification>()

    private val channel = BroadcastChannel<Collection<StatusBarNotification>>(Channel.CONFLATED)

    override fun asFlow(): Flow<Collection<StatusBarNotification>> = channel.asFlow()

    override fun getAll(): Collection<StatusBarNotification> = cache.values

    override suspend fun add(sbn: StatusBarNotification) {
        cache[sbn.key] = sbn
        updateChannel()
    }

    override suspend fun remove(sbn: StatusBarNotification) {
        remove(sbn.key)
        updateChannel()
    }

    override suspend fun remove(key: String) {
        cache.remove(key)
    }

    override suspend fun clear() {
        cache.clear()
        updateChannel()
    }


    private suspend fun updateChannel() {
        channel.send(getAll())
    }
}