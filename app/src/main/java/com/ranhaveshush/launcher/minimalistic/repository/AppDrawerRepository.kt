package com.ranhaveshush.launcher.minimalistic.repository

import com.ranhaveshush.launcher.minimalistic.data.app.DrawerAppTransformer
import com.ranhaveshush.launcher.minimalistic.data.app.InstalledAppsDataSource
import com.ranhaveshush.launcher.minimalistic.vo.DrawerApp
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDrawerRepository(
    private val dataSource: InstalledAppsDataSource,
    private val dataTransformer: DrawerAppTransformer
) {
    fun listApps(filter: String = ""): Flow<Resource<List<DrawerApp>>> = dataSource.asFlow().map { resolveInfos ->
        val apps = resolveInfos.map { resolveInfo ->
            dataTransformer.transform(resolveInfo)
        }

        val normalizedFilter = filter.toLowerCase()
        val filteredApps = apps.filter { appItem ->
            appItem.name.startsWith(normalizedFilter)
        }

        val sortedApps = filteredApps.sortedBy { appItem ->
            appItem.name
        }

        Resource.success(sortedApps)
    }
}
