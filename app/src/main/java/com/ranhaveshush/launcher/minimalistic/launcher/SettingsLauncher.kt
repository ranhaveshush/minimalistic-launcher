package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application

/**
 * An OS app settings launcher.
 */
interface SettingsLauncher {
    /**
     * Launches the App's info settings for the given [packageName].
     *
     * @return true in case of success, otherwise false.
     */
    fun launchAppDetails(context: Application, packageName: String): Boolean
}