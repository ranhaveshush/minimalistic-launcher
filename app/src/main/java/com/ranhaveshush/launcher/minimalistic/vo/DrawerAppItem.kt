package com.ranhaveshush.launcher.minimalistic.vo

import android.graphics.drawable.Drawable

/**
 * A home screen app [ValueObject].
 */
data class DrawerAppItem(
    val packageName: String,
    val name: String,
    val label: String,
    val icon: Drawable
) : ValueObject
