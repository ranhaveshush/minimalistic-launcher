package com.ranhaveshush.launcher.minimalistic.data.app

import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.ranhaveshush.launcher.minimalistic.vo.HomeApp

/**
 * An [HomeAppTransformer] implementation.
 */
class HomeAppTransformerImpl(
    private val packageManager: PackageManager
) : HomeAppTransformer {
    override fun transform(data: ResolveInfo): HomeApp {
        val packageName = data.activityInfo.packageName
        val activityName = data.activityInfo.name
        val label = data.loadLabel(packageManager).toString().capitalize()
        val name = label.toLowerCase()
        return HomeApp(packageName, activityName, name, label)
    }
}
