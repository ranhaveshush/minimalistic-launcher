package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.ComponentName
import android.content.Intent
import com.ranhaveshush.launcher.minimalistic.ktx.safeLaunch

/**
 * An [AppsLauncher] implementation.
 */
class AppsLauncherImpl : AppsLauncher {
    override fun launch(context: Application, componentName: ComponentName): Boolean {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            component = componentName
        }

        return intent.safeLaunch(context)
    }
}