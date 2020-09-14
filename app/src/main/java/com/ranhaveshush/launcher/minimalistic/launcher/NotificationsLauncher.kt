package com.ranhaveshush.launcher.minimalistic.launcher

import com.ranhaveshush.launcher.minimalistic.vo.Notification

/**
 * An notification launcher.
 */
interface NotificationsLauncher {
    /**
     * Launches a sender App with the given [Notification].
     */
    fun launch(notification: Notification)
}
