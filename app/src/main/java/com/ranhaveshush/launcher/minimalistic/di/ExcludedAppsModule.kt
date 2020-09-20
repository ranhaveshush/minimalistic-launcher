package com.ranhaveshush.launcher.minimalistic.di

import com.ranhaveshush.launcher.minimalistic.notification.ExcludedApps
import com.ranhaveshush.launcher.minimalistic.notification.ExcludedAppsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ExcludedAppsModule {
    @Binds
    abstract fun bindExcludedApps(excludedApps: ExcludedAppsImpl): ExcludedApps
}
