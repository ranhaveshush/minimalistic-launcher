package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * A [SettingsLauncher] implementation.
 */
class SettingsLauncherImpl : SettingsLauncher {
    override fun launchAppDetails(context: Application, packageName: String): Boolean {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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