package com.ranhaveshush.launcher.minimalistic.notification

import javax.inject.Inject

class ExcludedAppsImpl @Inject constructor() : ExcludedApps {
    private val excludedPackages = listOf(
        "com.whatsapp",
        "com.facebook.orca",
        "org.telegram.messenger",
        "com.facebook.mlite",
        "com.tencent.mm"
    )

    override fun isExcluded(packageName: String): Boolean {
        return excludedPackages.contains(packageName)
    }
}
