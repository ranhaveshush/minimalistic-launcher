package com.ranhaveshush.launcher.minimalistic.vo

import android.graphics.drawable.Drawable

/**
 * A notification [ValueObject].
 */
data class NotificationItem(
    val id: Int,
    val packageName: String,
    val appIcon: Drawable,
    val appLabel: String,
    val postedAt: NotificationTime,
    val title: String,
    val text: String
) : ValueObject

data class NotificationTime(private val time: Long) {
    val value: String
        get() = toString()

    override fun toString(): String {
        val passedTime = System.currentTimeMillis() - time
        val seconds = passedTime / 1000
        if (seconds == 0L) return "${passedTime}ms"
        val minutes = seconds / 60
        if (minutes == 0L) return "${seconds}s"
        val hours = minutes / 60
        if (hours == 0L) return "${minutes}m"
        val days = hours / 24
        if (days == 0L) return "${hours}h"
        val weeks = days / 7
        if (weeks == 0L) return "${days}d"
        val months = weeks / 4
        return if (months == 0L) "${weeks}w" else "ages"
    }
}