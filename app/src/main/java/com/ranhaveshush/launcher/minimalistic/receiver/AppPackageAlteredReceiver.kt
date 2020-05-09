package com.ranhaveshush.launcher.minimalistic.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.ranhaveshush.launcher.minimalistic.data.app.InstalledAppsDataSource
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils

/**
 * A [BroadcastReceiver] implementation,
 * that listens to app install and uninstall events.
 */
class AppPackageAlteredReceiver : BroadcastReceiver() {
    private lateinit var dataSource: InstalledAppsDataSource

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null && !intent.action.isNullOrEmpty() && actions.contains(intent.action!!)) {
            initDataSource(context)

            intent.dataString?.let { data ->
                val packageName = data.replace("$PACKAGE_SCHEME:", "")
                dataSource.onAppAltered(packageName)
            }
        }
    }

    private fun initDataSource(context: Context) {
        if (!this::dataSource.isInitialized) {
            dataSource = InjectorUtils.provideInstalledAppsDataSource(context.packageManager)
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
