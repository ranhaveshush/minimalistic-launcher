package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.PendingIntent
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem

/**
 * A [NotificationsLauncher] implementation.
 */
class NotificationsLauncherImpl : NotificationsLauncher {
    override fun launch(notificationItem: NotificationItem) {
        try {
            notificationItem.contentIntent?.send()
        } catch (e: PendingIntent.CanceledException) {
            // The PendingIntent is no longer allowing more intents to be sent through it.
            // Do nothing.
        }
    }
}