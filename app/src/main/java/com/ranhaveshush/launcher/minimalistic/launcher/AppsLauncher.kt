package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.Intent

/**
 * An app launcher.
 */
class AppsLauncher {
    /**
     * Launches an App with the given [packageName].
     */
    fun launch(context: Application, packageName: String) {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            `package` = packageName
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}