package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * An OS settings launcher.
 */
class SettingsLauncher {
    /**
     * Launches the App's info settings for the given [packageName].
     *
     * @return true in case of success, otherwise false.
     */
    fun launchAppDetails(context: Application, packageName: String): Boolean {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            data = Uri.parse("package:$packageName")
        }

        return if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
            true
        } else {
            false
        }
    }
}