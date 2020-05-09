package com.ranhaveshush.launcher.minimalistic.data.notification

import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.data.DataTransformer
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem

interface NotificationItemTransformer : DataTransformer<StatusBarNotification, NotificationItem>
