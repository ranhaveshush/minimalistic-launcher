package com.ranhaveshush.launcher.minimalistic.ktx

import android.content.Context
import android.content.Intent

fun Intent.safeLaunch(context: Context): Boolean =
    if (resolveActivity(context.packageManager) != null) {
        context.startActivity(this)
        true
    } else {
        false
    }
