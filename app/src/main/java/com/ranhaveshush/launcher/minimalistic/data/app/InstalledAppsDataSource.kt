package com.ranhaveshush.launcher.minimalistic.data.app

import android.content.pm.ResolveInfo
import kotlinx.coroutines.flow.Flow

/**
 * A data source of the currently installed [apps][ResolveInfo] on the device.
 */
interface InstalledAppsDataSource {
    fun asFlow(): Flow<Collection<ResolveInfo>>
    fun onAppAltered(packageName: String)
}
