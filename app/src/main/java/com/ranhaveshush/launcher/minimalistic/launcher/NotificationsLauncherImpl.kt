package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.PendingIntent
import com.ranhaveshush.launcher.minimalistic.vo.Notification
import javax.inject.Inject

/**
 * A [NotificationsLauncher] implementation.
 */
class NotificationsLauncherImpl @Inject constructor() : NotificationsLauncher {
    override fun launch(notification: Notification) {
        try {
            notification.contentIntent?.send()
        } catch (e: PendingIntent.CanceledException) {
            // The PendingIntent is no longer allowing more intents to be sent through it.
            // Do nothing.
        }
    }
}
