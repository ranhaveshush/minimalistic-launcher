package com.ranhaveshush.launcher.minimalistic.repository

import com.ranhaveshush.launcher.minimalistic.data.app.HomeAppItemTransformer
import com.ranhaveshush.launcher.minimalistic.data.app.InstalledAppsDataSource
import com.ranhaveshush.launcher.minimalistic.vo.HomeAppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeRepository(
    private val dataSource: InstalledAppsDataSource,
    private val dataTransformer: HomeAppItemTransformer
) {
    fun listApps(): Flow<Resource<List<HomeAppItem>>> = dataSource.asFlow().map { resolveInfos ->
        val appItems = resolveInfos.map { resolveInfo ->
            dataTransformer.transform(resolveInfo)
        }

        val filteredAppItems = appItems.subList(0, 7)

        val sortedAppItems = filteredAppItems.sortedBy { appItem ->
            appItem.name
        }

        Resource.success(sortedAppItems)
    }
}
