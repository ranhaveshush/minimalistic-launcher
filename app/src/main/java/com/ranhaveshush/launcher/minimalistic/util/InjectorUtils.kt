package com.ranhaveshush.launcher.minimalistic.util

import android.content.pm.PackageManager
import com.ranhaveshush.launcher.minimalistic.repository.AppDrawerRepository
import com.ranhaveshush.launcher.minimalistic.viewmodel.AppDrawerViewModelFactory

class InjectorUtils {
    fun provideAppDrawerViewModelFactory(packageManager: PackageManager) = AppDrawerViewModelFactory(getAppDrawerRepository(packageManager))

    fun getAppDrawerRepository(packageManager: PackageManager) = AppDrawerRepository(packageManager)
}
