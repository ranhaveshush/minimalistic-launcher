package com.ranhaveshush.launcher.minimalistic.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.room.Room
import com.ranhaveshush.launcher.minimalistic.db.AppDatabase
import com.ranhaveshush.launcher.minimalistic.repository.AppDrawerRepository
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository
import com.ranhaveshush.launcher.minimalistic.viewmodel.AppDrawerViewModelFactory
import com.ranhaveshush.launcher.minimalistic.viewmodel.HomeViewModelFactory
import com.ranhaveshush.launcher.minimalistic.viewmodel.NotificationViewModelFactory

class InjectorUtils {
    fun provideNotificationViewModelFactory(applicationContext: Context) =
        NotificationViewModelFactory(provideNotificationRepository(applicationContext))

    fun provideNotificationRepository(applicationContext: Context) = NotificationRepository(provideDatabase(applicationContext).notificationDao())

    fun provideHomeViewModelFactory(packageManager: PackageManager) = HomeViewModelFactory(provideHomeRepository(packageManager))

    fun provideHomeRepository(packageManager: PackageManager) = HomeRepository(packageManager)

    fun provideAppDrawerViewModelFactory(packageManager: PackageManager) = AppDrawerViewModelFactory(provideAppDrawerRepository(packageManager))

    fun provideAppDrawerRepository(packageManager: PackageManager) = AppDrawerRepository(packageManager)

    private fun provideDatabase(applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "database"
    ).build()
}
