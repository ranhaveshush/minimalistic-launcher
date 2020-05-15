package com.ranhaveshush.launcher.minimalistic.vo

/**
 * A home screen app [ValueObject].
 */
data class HomeApp(
    val packageName: String,
    val activityName: String,
    val name: String,
    val label: String
) : ValueObject
