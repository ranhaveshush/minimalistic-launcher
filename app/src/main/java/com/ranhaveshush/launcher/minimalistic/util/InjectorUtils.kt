package com.ranhaveshush.launcher.minimalistic.util

import android.content.pm.PackageManager
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.viewmodel.HomeViewModelFactory

class InjectorUtils {
    fun provideHomeViewModelFactory(packageManager: PackageManager) = HomeViewModelFactory(getHomeRepository(packageManager))

    fun getHomeRepository(packageManager: PackageManager) = HomeRepository(packageManager)
}
