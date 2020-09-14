package com.ranhaveshush.launcher.minimalistic.data.notification

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.service.notification.StatusBarNotification
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmap
import com.ranhaveshush.launcher.minimalistic.vo.NotificationTime
import javax.inject.Inject

/**
 * A [NotificationTransformer] implementation.
 */
class NotificationTransformerImpl @Inject constructor(
    private val context: Context
) : NotificationTransformer {
    override fun transform(data: StatusBarNotification): com.ranhaveshush.launcher.minimalistic.vo.Notification {
        val notification = data.notification

        val appInfo: ApplicationInfo = notification.extras.getParcelable("android.appInfo")!!
        val appLabel = appInfo.loadLabel(context.packageManager).toString()
        val appIconDrawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notification.smallIcon.loadDrawable(context)
        } else {
            appInfo.loadIcon(context.packageManager)
        }
        val appIconBitmap = appIconDrawable.toBitmap(
            appIconDrawable.intrinsicWidth, appIconDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val postedAt = notification.`when`
        val title = notification.extras.get(NotificationCompat.EXTRA_TITLE)?.toString() ?: ""
        val text = notification.extras.get(NotificationCompat.EXTRA_TEXT)?.toString() ?: ""
        val contentIntent = notification.contentIntent
        val deleteIntent = notification.deleteIntent

        return com.ranhaveshush.launcher.minimalistic.vo.Notification(
            data.key,
            data.packageName,
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
