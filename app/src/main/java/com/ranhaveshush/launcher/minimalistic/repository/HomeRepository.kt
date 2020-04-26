package com.ranhaveshush.launcher.minimalistic.repository

import android.content.Intent
import android.content.pm.PackageManager
import com.ranhaveshush.launcher.minimalistic.vo.HomeAppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository(private val packageManager: PackageManager) {
    fun listApps(): Flow<Resource<List<HomeAppItem>>> = flow {
        emit(Resource.loading())

        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        val appItems = resolveInfoList.map {
            val packageName = it.activityInfo.packageName
            val label = it.loadLabel(packageManager).toString().capitalize()
            val name = label.toLowerCase()
            HomeAppItem(packageName, name, label)
        }

        val filteredAppItems = appItems.subList(0, 7)

        val sortedAppItems = filteredAppItems.sortedBy {
            it.name
        }

        emit(Resource.success(sortedAppItems))
    }
}