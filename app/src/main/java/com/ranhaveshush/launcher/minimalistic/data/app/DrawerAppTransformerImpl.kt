package com.ranhaveshush.launcher.minimalistic.data.app

import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppItem

/**
 * An [DrawerAppItemTransformer] implementation.
 */
class DrawerAppItemTransformerImpl(
    private val packageManager: PackageManager
) : DrawerAppItemTransformer {
    override fun transform(data: ResolveInfo): DrawerAppItem {
        val packageName = data.activityInfo.packageName
        val activityName = data.activityInfo.name
        val label = data.loadLabel(packageManager).toString().capitalize()
        val name = label.toLowerCase()
        val icon = data.loadIcon(packageManager)
        return DrawerAppItem(packageName, activityName, name, label, icon)
    }
}
