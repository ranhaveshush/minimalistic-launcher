package com.ranhaveshush.launcher.minimalistic.data.app

import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.ranhaveshush.launcher.minimalistic.vo.DrawerApp
import javax.inject.Inject

/**
 * An [DrawerAppTransformer] implementation.
 */
class DrawerAppTransformerImpl @Inject constructor(
    private val packageManager: PackageManager
) : DrawerAppTransformer {
    override fun transform(data: ResolveInfo): DrawerApp {
        val packageName = data.activityInfo.packageName
        val activityName = data.activityInfo.name
        val label = data.loadLabel(packageManager).toString().capitalize()
        val name = label.toLowerCase()
        val icon = data.loadIcon(packageManager)
        return DrawerApp(packageName, activityName, name, label, icon)
    }
}
