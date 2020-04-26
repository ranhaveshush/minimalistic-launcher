package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application

/**
 * An app launcher.
 */
interface AppsLauncher {
    /**
     * Launches an App with the given [packageName].
     */
    fun launch(context: Application, packageName: String)
}