package com.ranhaveshush.launcher.minimalistic.di

import android.content.Context
import android.content.pm.PackageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {
    @Provides
    fun provideContext(
        @ApplicationContext context: Context
    ): Context = context

    @Provides
    fun providePackageManager(
        @ApplicationContext context: Context
    ): PackageManager = context.packageManager
}
