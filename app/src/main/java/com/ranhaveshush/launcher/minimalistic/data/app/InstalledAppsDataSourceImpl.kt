package com.ranhaveshush.launcher.minimalistic.data.app

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A [InstalledAppsDataSource] implementation.
 */
@Singleton
class InstalledAppsDataSourceImpl @Inject constructor(
    private val packageManager: PackageManager,
    private val cache: MutableMap<String, ResolveInfo>,
    private val channel: BroadcastChannel<Collection<ResolveInfo>>
) : InstalledAppsDataSource {
    init {
        refresh()
        updateChannel()
    }

    override fun asFlow(): Flow<Collection<ResolveInfo>> = channel.asFlow()

    override fun onAppAdded(packageName: String) {
        refresh()
        updateChannel()
    }

    override fun onAppRemoved(packageName: String) {
        cache.remove(packageName)
        updateChannel()
    }

    private fun refresh() {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfoList = packageManager.queryIntentActivities(intent, 0)
        resolveInfoList.forEach {
            val packageName = getPackageName(it)
            cache[packageName] = it
        }
    }

    private fun getPackageName(resolveInfo: ResolveInfo): String {
        return resolveInfo.activityInfo.packageName
    }

    private fun updateChannel() {
        channel.offer(cache.values)
    }
}
