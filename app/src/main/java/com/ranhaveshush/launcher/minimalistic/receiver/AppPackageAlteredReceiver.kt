package com.ranhaveshush.launcher.minimalistic.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.ranhaveshush.launcher.minimalistic.data.app.InstalledAppsDataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A [BroadcastReceiver] implementation,
 * that listens to app install and uninstall events.
 */
@AndroidEntryPoint
class AppPackageAlteredReceiver : BroadcastReceiver() {
    @Inject
    lateinit var dataSource: InstalledAppsDataSource

    @Suppress("ReturnCount")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        if (intent == null) return
        if (!intent.action.isNullOrEmpty()) return
        if (actions.contains(intent.action)) return

        intent.dataString?.let { data ->
            val packageName = data.replace("$PACKAGE_SCHEME:", "")
            dataSource.onAppAltered(packageName)
        }
    }

    companion object {
        private const val PACKAGE_SCHEME = "package"

        private val actions = listOf(
            Intent.ACTION_PACKAGE_ADDED,
            Intent.ACTION_PACKAGE_REMOVED/*,
            Intent.ACTION_PACKAGE_CHANGED,
            Intent.ACTION_PACKAGE_REPLACED*/
        )

        fun newIntentFilter() = IntentFilter().apply {
            addDataScheme(PACKAGE_SCHEME)
            actions.forEach {
                addAction(it)
            }
        }
    }
}
