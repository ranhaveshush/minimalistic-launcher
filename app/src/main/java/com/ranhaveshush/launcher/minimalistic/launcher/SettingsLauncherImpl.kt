package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.ranhaveshush.launcher.minimalistic.ktx.safeLaunch
import javax.inject.Inject

/**
 * A [SettingsLauncher] implementation.
 */
class SettingsLauncherImpl @Inject constructor() : SettingsLauncher {
    override fun launchAppDetails(context: Application, packageName: String): Boolean {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.parse("package:$packageName")
        }

        return intent.safeLaunch(context)
    }

    override fun launchWallpaperChooser(context: Application): Boolean {
        val intent = Intent(Intent.ACTION_SET_WALLPAPER).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        return intent.safeLaunch(context)
    }
}
