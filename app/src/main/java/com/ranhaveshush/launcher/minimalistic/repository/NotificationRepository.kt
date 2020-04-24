package com.ranhaveshush.launcher.minimalistic.repository

import com.ranhaveshush.launcher.minimalistic.db.notification.NotificationDao
import com.ranhaveshush.launcher.minimalistic.db.notification.adapt
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotificationRepository(private val notificationDao: NotificationDao) : NotificationDao by notificationDao {
    fun getAllNotifications(): Flow<List<NotificationItem>> = notificationDao.getAll().map {
        it.map { notificationEntity ->
            notificationEntity.adapt()
        }
    }
}