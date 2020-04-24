package com.ranhaveshush.launcher.minimalistic.service

import android.app.Notification
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.graphics.drawable.DrawableCompat
import com.ranhaveshush.launcher.minimalistic.db.notification.NotificationEntity
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NotificationService : NotificationListenerService() {
    private val TAG = "NotificationService"

    private lateinit var serviceScope: CoroutineScope

    private lateinit var repository: NotificationRepository

    private var isConnected: Boolean = false

    override fun onListenerConnected() {
        Log.d(TAG, "onListenerConnected()")

        serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        repository = InjectorUtils().provideNotificationRepository(applicationContext)

        isConnected = true
    }

    override fun onListenerDisconnected() {
        Log.d(TAG, "onListenerDisconnected()")

        if (this::serviceScope.isInitialized) {
            serviceScope.cancel()
        }

        isConnected = false
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        Log.d(TAG, "onNotificationPosted($sbn)")

        sbn?.let {
            serviceScope.launch {
                // TODO: create an adapter from StatusBarNotification to NotificationEntity.
                val notification = sbn.notification

                val appInfo = notification.extras.getParcelable("android.appInfo") as ApplicationInfo
                val appLabel = appInfo.loadLabel(packageManager).toString()
                val appIcon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    var vectorDrawable = notification.smallIcon.loadDrawable(applicationContext)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        vectorDrawable = (DrawableCompat.wrap(vectorDrawable)).mutate()
                    }
                    val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(bitmap)
                    vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
                    vectorDrawable.draw(canvas)

                    BitmapDrawable(applicationContext.resources, bitmap)
                } else {
                    appInfo.loadIcon(packageManager)
                }

                val postedAt = notification.`when`
                val title = notification.extras.getString(Notification.EXTRA_TITLE, "")
                val text = notification.extras.getString(Notification.EXTRA_TEXT, "")

                if (title.isNullOrEmpty()) {
                    return@launch
                }

                val notificationEntity = NotificationEntity(
                    sbn.id,
                    sbn.packageName,
                    appIcon,
                    appLabel,
                    postedAt,
                    title,
                    text
                )

                repository.insert(notificationEntity)
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        Log.d(TAG, "onNotificationRemoved($sbn)")

        sbn?.let {
            serviceScope.launch {
//                notificationRepository.delete(notificationEntity)
            }
        }
    }
}