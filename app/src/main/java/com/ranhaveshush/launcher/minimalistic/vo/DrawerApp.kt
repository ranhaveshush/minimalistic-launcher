package com.ranhaveshush.launcher.minimalistic.vo

import android.graphics.drawable.Drawable

/**
 * A app drawer screen app [ValueObject].
 */
data class DrawerApp(
    val packageName: String,
    val activityName: String,
    val name: String,
    val label: String,
    val icon: Drawable
) : ValueObject
