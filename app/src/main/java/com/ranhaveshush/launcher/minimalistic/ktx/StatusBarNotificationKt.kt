package com.ranhaveshush.launcher.minimalistic.ktx

import android.service.notification.StatusBarNotification

val StatusBarNotification.simpleKey: String
    get() = "${packageName}|${id}"
