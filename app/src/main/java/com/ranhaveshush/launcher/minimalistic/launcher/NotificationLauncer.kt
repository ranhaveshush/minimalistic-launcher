package com.ranhaveshush.launcher.minimalistic.launcher

import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem

/**
 * An notification launcher.
 */
interface NotificationsLauncher {
    /**
     * Launches a sender App with the given [NotificationItem].
     */
    fun launch(notificationItem: NotificationItem)
}