package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.Notification

/**
 * A click listener for an [Notification].
 */
interface NotificationItemClickListener {
    fun onNotificationClick(notification: Notification)
}
