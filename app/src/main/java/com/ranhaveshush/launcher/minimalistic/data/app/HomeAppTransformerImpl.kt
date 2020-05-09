package com.ranhaveshush.launcher.minimalistic.data.app

import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.ranhaveshush.launcher.minimalistic.vo.HomeAppItem

/**
 * An [HomeAppItemTransformer] implementation.
 */
class HomeAppItemTransformerImpl(
    private val packageManager: PackageManager
) : HomeAppItemTransformer {
    override fun transform(data: ResolveInfo): HomeAppItem {
        val packageName = data.activityInfo.packageName
        val activityName = data.activityInfo.name
        val label = data.loadLabel(packageManager).toString().capitalize()
        val name = label.toLowerCase()
        return HomeAppItem(packageName, activityName, name, label)
    }
}
