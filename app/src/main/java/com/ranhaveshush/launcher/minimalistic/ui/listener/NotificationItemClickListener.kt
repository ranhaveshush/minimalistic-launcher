package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem

/**
 * A click listener or an [NotificationItem].
 */
interface NotificationItemClickListener {
    fun onNotificationClick(notificationItem: NotificationItem)
}
