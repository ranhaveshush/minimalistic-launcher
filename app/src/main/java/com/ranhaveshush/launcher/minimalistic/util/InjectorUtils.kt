package com.ranhaveshush.launcher.minimalistic.util

import android.content.pm.PackageManager
import com.ranhaveshush.launcher.minimalistic.repository.AppDrawerRepository
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.viewmodel.AppDrawerViewModelFactory
import com.ranhaveshush.launcher.minimalistic.viewmodel.HomeViewModelFactory

class InjectorUtils {
    fun provideHomeViewModelFactory(packageManager: PackageManager) = HomeViewModelFactory(getHomeRepository(packageManager))

    fun getHomeRepository(packageManager: PackageManager) = HomeRepository(packageManager)

    fun provideAppDrawerViewModelFactory(packageManager: PackageManager) = AppDrawerViewModelFactory(getAppDrawerRepository(packageManager))

    fun getAppDrawerRepository(packageManager: PackageManager) = AppDrawerRepository(packageManager)
}
