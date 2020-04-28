package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.ComponentName

/**
 * An app launcher.
 */
interface AppsLauncher {
    /**
     * Launches an App with the given [ComponentName].
     *
     * @return true in case the launch was successful, otherwise false.
     */
    fun launch(context: Application, componentName: ComponentName): Boolean
}