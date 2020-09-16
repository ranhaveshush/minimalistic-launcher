package com.ranhaveshush.launcher.minimalistic.data.notification

import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.ktx.simpleKey
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A [NotificationDataSource] implementation.
 */
@Singleton
class NotificationDataSourceImpl @Inject constructor(
    private val cache: MutableMap<String, StatusBarNotification>,
    private val channel: BroadcastChannel<Collection<StatusBarNotification>>
) : NotificationDataSource {
    override fun asFlow(): Flow<Collection<StatusBarNotification>> = channel.asFlow()

    override suspend fun add(sbn: StatusBarNotification) {
        val key = sbn.simpleKey
        cache[key] = sbn
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
        channel.send(cache.values)
    }
}
