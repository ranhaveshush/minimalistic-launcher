package com.ranhaveshush.launcher.minimalistic.vo

/**
 * A home screen app [ValueObject].
 */
data class HomeAppItem(
    val packageName: String,
    val activityName: String,
    val name: String,
    val label: String
) : ValueObject
