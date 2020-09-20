package com.ranhaveshush.launcher.minimalistic.notification

interface ExcludedApps {
    fun isExcluded(packageName: String): Boolean
}
