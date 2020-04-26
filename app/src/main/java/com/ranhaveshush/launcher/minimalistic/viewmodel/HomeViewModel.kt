package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncher
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.vo.HomeAppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val repository: HomeRepository,
    private val appsLauncher: AppsLauncher,
    private val settingsLauncher: SettingsLauncher
) : ViewModel(), AppsLauncher by appsLauncher, SettingsLauncher by settingsLauncher {
    val apps: LiveData<Resource<List<HomeAppItem>>> = liveData {
        emitSource(repository.listApps().asLiveData(Dispatchers.Default))
    }
}
