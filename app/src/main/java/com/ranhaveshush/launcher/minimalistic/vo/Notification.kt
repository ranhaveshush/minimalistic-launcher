package com.ranhaveshush.launcher.minimalistic.vo

import android.app.PendingIntent
import android.graphics.drawable.Drawable

/**
 * A notification [ValueObject].
 */
data class Notification(
    val key: String,
    val packageName: String,
    val postTime: Long,
    val appIcon: Drawable,
    val appLabel: String,
    val title: String,
    val text: String,
    val contentIntent: PendingIntent?,
    val deleteIntent: PendingIntent?
) : ValueObject {
    @Suppress("MagicNumber", "ReturnCount")
    fun getElapsedTime(): String {
        val elapsedTime = System.currentTimeMillis() - postTime
        val seconds = elapsedTime / 1000
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
