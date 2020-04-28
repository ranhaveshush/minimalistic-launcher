package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.ComponentName
import android.content.Intent

/**
 * An [AppsLauncher] implementation.
 */
class AppsLauncherImpl : AppsLauncher {
    override fun launch(context: Application, componentName: ComponentName) {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            component = componentName
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}