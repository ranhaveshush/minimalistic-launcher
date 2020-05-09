package com.ranhaveshush.launcher.minimalistic.util

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.data.app.DrawerAppItemTransformer
import com.ranhaveshush.launcher.minimalistic.data.app.DrawerAppItemTransformerImpl
import com.ranhaveshush.launcher.minimalistic.data.app.HomeAppItemTransformer
import com.ranhaveshush.launcher.minimalistic.data.app.HomeAppItemTransformerImpl
import com.ranhaveshush.launcher.minimalistic.data.app.InstalledAppsDataSource
import com.ranhaveshush.launcher.minimalistic.data.app.InstalledAppsDataSourceImpl
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationDataSource
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationDataSourceImpl
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationItemTransformer
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationItemTransformerImpl
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
    private lateinit var installedAppsDataSource: InstalledAppsDataSource
    private lateinit var notificationDataSource: NotificationDataSource

    fun provideNotificationViewModelFactory(applicationContext: Context) = NotificationViewModelFactory(
        provideNotificationRepository(applicationContext),
        provideNotificationsLauncher()
    )

    fun provideNotificationRepository(applicationContext: Context) = NotificationRepository(
        provideNotificationDataSource(),
        provideNotificationItemTransformer(applicationContext)
    )

    fun provideHomeViewModelFactory(packageManager: PackageManager) = HomeViewModelFactory(
        provideHomeRepository(packageManager),
        provideAppsLauncher(),
        provideSettingsLauncher()
    )

    fun provideHomeRepository(packageManager: PackageManager) = HomeRepository(
        provideInstalledAppsDataSource(packageManager),
        provideHomeAppItemTransformer(packageManager)
    )

    fun provideAppDrawerViewModelFactory(packageManager: PackageManager) = AppDrawerViewModelFactory(
        provideAppDrawerRepository(packageManager),
        provideAppsLauncher(),
        provideSettingsLauncher()
    )

    fun provideAppDrawerRepository(packageManager: PackageManager) = AppDrawerRepository(
        provideInstalledAppsDataSource(packageManager),
        provideDrawerAppItemTransformer(packageManager)
    )

    fun provideInstalledAppsDataSource(packageManager: PackageManager): InstalledAppsDataSource {
        if (!this::installedAppsDataSource.isInitialized) {
            val installedAppsCache: MutableMap<String, ResolveInfo> = Collections.synchronizedMap(HashMap())
            val installedAppsChannel = BroadcastChannel<Collection<ResolveInfo>>(Channel.CONFLATED)

            installedAppsDataSource = InstalledAppsDataSourceImpl(
                packageManager,
                installedAppsCache,
                installedAppsChannel
            )
        }

        return installedAppsDataSource
    }

    fun provideNotificationDataSource(): NotificationDataSource {
        if (!this::notificationDataSource.isInitialized) {
            val notificationCache: MutableMap<String, StatusBarNotification> = Collections.synchronizedMap(HashMap())
            val notificationChannel = BroadcastChannel<Collection<StatusBarNotification>>(Channel.CONFLATED)

            notificationDataSource = NotificationDataSourceImpl(
                notificationCache,
                notificationChannel
            )
        }

        return notificationDataSource
    }

    fun provideHomeAppItemTransformer(packageManager: PackageManager): HomeAppItemTransformer = HomeAppItemTransformerImpl(packageManager)

    fun provideDrawerAppItemTransformer(packageManager: PackageManager): DrawerAppItemTransformer = DrawerAppItemTransformerImpl(packageManager)

    fun provideNotificationItemTransformer(context: Context): NotificationItemTransformer = NotificationItemTransformerImpl(context)

    fun provideAppsLauncher(): AppsLauncher = AppsLauncherImpl()

    fun provideSettingsLauncher(): SettingsLauncher = SettingsLauncherImpl()

    fun provideNotificationsLauncher(): NotificationsLauncher = NotificationsLauncherImpl()
}
