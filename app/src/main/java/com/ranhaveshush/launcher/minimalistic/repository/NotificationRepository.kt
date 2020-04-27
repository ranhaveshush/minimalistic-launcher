package com.ranhaveshush.launcher.minimalistic.repository

import android.content.Context
import com.ranhaveshush.launcher.minimalistic.cache.NotificationCache
import com.ranhaveshush.launcher.minimalistic.cache.NotificationTransformer
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotificationRepository(
    private val context: Context,
    private val notificationCache: NotificationCache,
    private val notificationTransformer: NotificationTransformer
) : NotificationCache by notificationCache {
    fun getAllNotifications(): Flow<Resource<List<NotificationItem>>> = notificationCache.asFlow().map {
        val notifications = it.map { sbn ->
            notificationTransformer.transform(context, sbn)
        }.sortedByDescending { notificationItem ->
            notificationItem.postTime
        }

        Resource.success(notifications)
    }
}