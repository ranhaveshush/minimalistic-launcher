package com.ranhaveshush.launcher.minimalistic.repository

import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationDataSource
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationItemTransformer
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotificationRepository(
    private val dataSource: NotificationDataSource,
    private val dataTransformer: NotificationItemTransformer
) {
    fun getAllNotifications(): Flow<Resource<List<NotificationItem>>> = dataSource.asFlow().map {
        val notifications = it.map { sbn ->
            dataTransformer.transform(sbn)
        }.sortedByDescending { notificationItem ->
            notificationItem.postTime
        }

        Resource.success(notifications)
    }

    suspend fun remove(key: String) {
        dataSource.remove(key)
    }
}
