package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncher
import com.ranhaveshush.launcher.minimalistic.repository.AppDrawerRepository

class AppDrawerViewModelFactory(
    private val repository: AppDrawerRepository,
    private val appsLauncher: AppsLauncher,
    private val settingsLauncher: SettingsLauncher
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return AppDrawerViewModel(repository, appsLauncher, settingsLauncher) as T
    }
}
