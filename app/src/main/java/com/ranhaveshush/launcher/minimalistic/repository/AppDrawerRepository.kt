package com.ranhaveshush.launcher.minimalistic.repository

import android.content.Intent
import android.content.pm.PackageManager
import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppDrawerRepository(private val packageManager: PackageManager) {
    fun listApps(filter: String = ""): Flow<Resource<List<DrawerAppItem>>> = flow {
        emit(Resource.loading())

        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        val appItems = resolveInfoList.map {
            val packageName = it.activityInfo.packageName
            val icon = it.loadIcon(packageManager)
            val label = it.loadLabel(packageManager).toString().capitalize()
            val name = label.toLowerCase()
            DrawerAppItem(packageName, name, label, icon)
        }

        val normalizedFilter = filter.toLowerCase()
        val filteredAppItems = appItems.filter {
            it.name.startsWith(normalizedFilter)
        }

        val sortedAppItems = filteredAppItems.sortedBy {
            it.name
        }

        emit(Resource.success(sortedAppItems))
    }
}
