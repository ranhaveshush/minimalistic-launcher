package com.ranhaveshush.launcher.minimalistic.launcher

import android.app.Application
import android.content.ComponentName

/**
 * An app launcher.
 */
interface AppsLauncher {
    /**
     * Launches an App with the given [ComponentName].
     */
    fun launch(context: Application, componentName: ComponentName)
}