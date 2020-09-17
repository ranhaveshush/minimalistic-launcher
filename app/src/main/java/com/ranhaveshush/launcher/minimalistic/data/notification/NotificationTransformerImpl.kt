package com.ranhaveshush.launcher.minimalistic.data.notification

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.service.notification.StatusBarNotification
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmap
import com.ranhaveshush.launcher.minimalistic.ktx.simpleKey
import com.ranhaveshush.launcher.minimalistic.vo.Notification
import javax.inject.Inject

/**
 * A [NotificationTransformer] implementation.
 */
class NotificationTransformerImpl @Inject constructor(
    private val context: Context
) : NotificationTransformer {
    override fun transform(data: StatusBarNotification): Notification {
        val notification = data.notification

        val appInfo: ApplicationInfo = notification.extras.getParcelable("android.appInfo")!!
        val appIconDrawable: Drawable? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notification.smallIcon.loadDrawable(context)
        } else {
            appInfo.loadIcon(context.packageManager)
        }
        val appIconBitmap: Bitmap? = appIconDrawable?.toBitmap(
            appIconDrawable.intrinsicWidth,
            appIconDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val appIcon = BitmapDrawable(context.resources, appIconBitmap)
        val appLabel = appInfo.loadLabel(context.packageManager).toString()

        val postTime = notification.`when`
        val title = notification.extras.get(NotificationCompat.EXTRA_TITLE)?.toString() ?: ""
        val text = notification.extras.get(NotificationCompat.EXTRA_TEXT)?.toString() ?: ""
        val contentIntent = notification.contentIntent
        val deleteIntent = notification.deleteIntent

        return Notification(
            data.simpleKey,
            data.packageName,
            postTime,
            appIcon,
            appLabel,
            title,
            text,
            contentIntent,
            deleteIntent
        )
    }
}
