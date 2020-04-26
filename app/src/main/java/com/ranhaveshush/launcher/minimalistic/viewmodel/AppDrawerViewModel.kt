package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncher
import com.ranhaveshush.launcher.minimalistic.repository.AppDrawerRepository
import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.Dispatchers

class AppDrawerViewModel(
    private val repository: AppDrawerRepository,
    private val appsLauncher: AppsLauncher,
    private val settingsLauncher: SettingsLauncher
) : ViewModel(), AppsLauncher by appsLauncher, SettingsLauncher by settingsLauncher {
    val searchQuery = MutableLiveData<String>()

    init {
        searchQuery.value = ""
    }

    val apps: LiveData<Resource<List<DrawerAppItem>>> = searchQuery.switchMap {
        listApps(it)
    }

    private fun listApps(searchQuery: String): LiveData<Resource<List<DrawerAppItem>>> = liveData {
        emitSource(repository.listApps(searchQuery).asLiveData(Dispatchers.Default))
    }
}
