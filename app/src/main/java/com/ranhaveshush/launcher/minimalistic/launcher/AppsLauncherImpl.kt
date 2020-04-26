package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.Intent

/**
 * An [AppsLauncher] implementation.
 */
class AppsLauncherImpl : AppsLauncher {
    override fun launch(context: Application, packageName: String) {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            `package` = packageName
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}