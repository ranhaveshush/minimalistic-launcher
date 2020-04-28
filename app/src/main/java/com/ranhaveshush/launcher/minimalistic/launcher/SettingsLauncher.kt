package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application

/**
 * An OS app settings launcher.
 */
interface SettingsLauncher {
    /**
     * Launches the App's info settings for the given [packageName].
     *
     * @return true in case the launch was successful, otherwise false.
     */
    fun launchAppDetails(context: Application, packageName: String): Boolean

    /**
     * Launches the Android OS  wallpaper chooser settings.
     *
     * @return true in case the launch was successful, otherwise false.
     */
    fun launchWallpaperChooser(context: Application): Boolean
}