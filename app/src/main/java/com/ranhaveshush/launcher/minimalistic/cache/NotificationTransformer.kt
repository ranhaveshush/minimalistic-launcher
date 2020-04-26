package com.ranhaveshush.launcher.minimalistic.cache

import android.content.Context
import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem

interface NotificationTransformer {
    fun transform(context: Context, sbn: StatusBarNotification): NotificationItem
}