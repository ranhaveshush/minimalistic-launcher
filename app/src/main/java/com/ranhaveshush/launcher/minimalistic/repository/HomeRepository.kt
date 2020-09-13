package com.ranhaveshush.launcher.minimalistic.repository

import android.content.pm.ApplicationInfo
import com.ranhaveshush.launcher.minimalistic.data.app.HomeAppTransformer
import com.ranhaveshush.launcher.minimalistic.data.app.InstalledAppsDataSource
import com.ranhaveshush.launcher.minimalistic.vo.HomeApp
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeRepository(
    private val dataSource: InstalledAppsDataSource,
    private val dataTransformer: HomeAppTransformer
) {
    fun listApps(): Flow<Resource<List<HomeApp>>> = dataSource.asFlow().map { resolveInfos ->
        val apps = resolveInfos.map { resolveInfo ->
            dataTransformer.transform(resolveInfo)
        }

        val filteredApps = apps.subList(0, 7)

        val sortedApps = filteredApps.sortedBy { appItem ->
            appItem.name
        }

        Resource.success(sortedApps)
    }
}
