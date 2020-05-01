package com.ranhaveshush.launcher.minimalistic.util

import android.content.Context
import android.content.pm.PackageManager
import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.cache.NotificationCache
import com.ranhaveshush.launcher.minimalistic.cache.NotificationCacheImpl
import com.ranhaveshush.launcher.minimalistic.cache.NotificationTransformer
import com.ranhaveshush.launcher.minimalistic.cache.NotificationTransformerImpl
import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncherImpl
import com.ranhaveshush.launcher.minimalistic.launcher.NotificationsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.NotificationsLauncherImpl
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncherImpl
import com.ranhaveshush.launcher.minimalistic.repository.AppDrawerRepository
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository
import com.ranhaveshush.launcher.minimalistic.viewmodel.AppDrawerViewModelFactory
import com.ranhaveshush.launcher.minimalistic.viewmodel.HomeViewModelFactory
import com.ranhaveshush.launcher.minimalistic.viewmodel.NotificationViewModelFactory
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import java.util.Collections

object InjectorUtils {
    private val notificationCache: MutableMap<String, StatusBarNotification> = Collections.synchronizedMap(HashMap())
    private val notificationChannel = BroadcastChannel<Collection<StatusBarNotification>>(Channel.CONFLATED)

    fun provideNotificationViewModelFactory(applicationContext: Context) =
        NotificationViewModelFactory(
            provideNotificationRepository(applicationContext),
            provideNotificationsLauncher()
        )

    fun provideNotificationRepository(applicationContext: Context) = NotificationRepository(
        applicationContext,
        provideNotificationCache(),
        provideNotificationTransformer()
    )

    fun provideHomeViewModelFactory(packageManager: PackageManager) = HomeViewModelFactory(
        provideHomeRepository(packageManager),
        provideAppsLauncher(),
        provideSettingsLauncher()
    )

    fun provideHomeRepository(packageManager: PackageManager) = HomeRepository(packageManager)

    fun provideAppDrawerViewModelFactory(packageManager: PackageManager) = AppDrawerViewModelFactory(
        provideAppDrawerRepository(packageManager),
        provideAppsLauncher(),
        provideSettingsLauncher()
    )

    fun provideAppDrawerRepository(packageManager: PackageManager) = AppDrawerRepository(packageManager)

    fun provideNotificationCache(): NotificationCache = NotificationCacheImpl(notificationCache, notificationChannel)

    fun provideNotificationTransformer(): NotificationTransformer = NotificationTransformerImpl()

    fun provideAppsLauncher(): AppsLauncher = AppsLauncherImpl()

    fun provideSettingsLauncher(): SettingsLauncher = SettingsLauncherImpl()

    fun provideNotificationsLauncher(): NotificationsLauncher = NotificationsLauncherImpl()
}
