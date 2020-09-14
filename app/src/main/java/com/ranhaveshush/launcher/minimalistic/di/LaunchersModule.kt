package com.ranhaveshush.launcher.minimalistic.di

import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncherImpl
import com.ranhaveshush.launcher.minimalistic.launcher.NotificationsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.NotificationsLauncherImpl
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class LaunchersModule {
    @Binds
    abstract fun bindAppsLauncher(launcher: AppsLauncherImpl): AppsLauncher

    @Binds
    abstract fun bindNotificationsLauncher(launcher: NotificationsLauncherImpl): NotificationsLauncher

    @Binds
    abstract fun bindSettingsLauncher(launcher: SettingsLauncherImpl): SettingsLauncher
}
