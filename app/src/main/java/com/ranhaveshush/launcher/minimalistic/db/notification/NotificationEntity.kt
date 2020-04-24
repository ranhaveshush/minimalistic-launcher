package com.ranhaveshush.launcher.minimalistic.db.notification

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem
import com.ranhaveshush.launcher.minimalistic.vo.NotificationTime

@Entity(tableName = "notification")
data class NotificationEntity(
    @PrimaryKey
    val id: Int,
    val packageName: String,
    val appIcon: Drawable,
    val appLabel: String,
    val postedAt: Long,
    val title: String,
    val text: String
)

fun NotificationEntity.adapt(): NotificationItem {
    return NotificationItem(
        id,
        packageName,
        appIcon,
        appLabel,
        NotificationTime(postedAt),
        title,
        text
    )
}