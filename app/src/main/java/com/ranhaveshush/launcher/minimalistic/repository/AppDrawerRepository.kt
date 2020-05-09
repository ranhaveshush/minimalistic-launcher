package com.ranhaveshush.launcher.minimalistic.repository

import com.ranhaveshush.launcher.minimalistic.data.app.DrawerAppItemTransformer
import com.ranhaveshush.launcher.minimalistic.data.app.InstalledAppsDataSource
import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDrawerRepository(
    private val dataSource: InstalledAppsDataSource,
    private val dataTransformer: DrawerAppItemTransformer
) {
    fun listApps(filter: String = ""): Flow<Resource<List<DrawerAppItem>>> = dataSource.asFlow().map { resolveInfos ->
        val appItems = resolveInfos.map { resolveInfo ->
            dataTransformer.transform(resolveInfo)
        }

        val normalizedFilter = filter.toLowerCase()
        val filteredAppItems = appItems.filter { appItem ->
            appItem.name.startsWith(normalizedFilter)
        }

        val sortedAppItems = filteredAppItems.sortedBy { appItem ->
            appItem.name
        }

        Resource.success(sortedAppItems)
    }
}
