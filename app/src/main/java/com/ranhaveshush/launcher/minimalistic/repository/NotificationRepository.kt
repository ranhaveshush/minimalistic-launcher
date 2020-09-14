package com.ranhaveshush.launcher.minimalistic.repository

import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationDataSource
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationTransformer
import com.ranhaveshush.launcher.minimalistic.vo.Notification
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val dataSource: NotificationDataSource, private val dataTransformer: NotificationTransformer
) {
    fun getAllNotifications(): Flow<Resource<List<Notification>>> = dataSource.asFlow().map {
        val statusBarNotifications = it.toList()
        val notifications = statusBarNotifications.map { sbn ->
            dataTransformer.transform(sbn)
        }.sortedByDescending { notificationItem ->
            notificationItem.postTime
        }

        Resource.success(notifications)
    }

    suspend fun clearAll() {
        dataSource.clear()
    }

    suspend fun remove(key: String) {
        dataSource.remove(key)
    }
}
