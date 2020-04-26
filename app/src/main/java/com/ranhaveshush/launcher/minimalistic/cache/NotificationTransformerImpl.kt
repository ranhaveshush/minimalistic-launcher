package com.ranhaveshush.launcher.minimalistic.cache

import android.app.Notification
import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.service.notification.StatusBarNotification
import androidx.core.graphics.drawable.toBitmap
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem
import com.ranhaveshush.launcher.minimalistic.vo.NotificationTime

class NotificationTransformerImpl : NotificationTransformer {
    override fun transform(context: Context, sbn: StatusBarNotification): NotificationItem {
        val notification = sbn.notification

        val appInfo = notification.extras.getParcelable("android.appInfo") as ApplicationInfo
        val appLabel = appInfo.loadLabel(context.packageManager).toString()
        val appIconDrawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notification.smallIcon.loadDrawable(context)
        } else {
            appInfo.loadIcon(context.packageManager)
        }
        val appIconBitmap = appIconDrawable.toBitmap(appIconDrawable.intrinsicWidth, appIconDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)

        val postedAt = notification.`when`
        val title = notification.extras.get(Notification.EXTRA_TITLE)?.toString() ?: ""
        val text = notification.extras.get(Notification.EXTRA_TEXT)?.toString() ?: ""
        val contentIntent = notification.contentIntent
        val deleteIntent = notification.deleteIntent

        return NotificationItem(
            sbn.key,
            sbn.packageName,
            BitmapDrawable(context.resources, appIconBitmap),
            appLabel,
            NotificationTime(postedAt),
            title,
            text,
            contentIntent,
            deleteIntent
        )
    }
}