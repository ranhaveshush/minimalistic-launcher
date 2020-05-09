package com.ranhaveshush.launcher.minimalistic

import android.app.Application
import com.ranhaveshush.launcher.minimalistic.receiver.AppPackageAlteredReceiver

/**
 * An [Application] implementation.
 */
class App : Application() {
    private val receiver = AppPackageAlteredReceiver()

    override fun onCreate() {
        super.onCreate()

        registerReceiver(receiver, AppPackageAlteredReceiver.newIntentFilter())
    }

    override fun onTerminate() {
        super.onTerminate()

        unregisterReceiver(receiver)
    }
}
