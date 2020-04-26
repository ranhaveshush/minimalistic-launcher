package com.ranhaveshush.launcher.minimalistic.launcher

import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem

/**
 * A [NotificationsLauncher] implementation.
 */
class NotificationsLauncherImpl : NotificationsLauncher {
    override fun launch(notificationItem: NotificationItem) {
        notificationItem.contentIntent?.send()
    }
}