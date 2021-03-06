package com.ranhaveshush.launcher.minimalistic.di

import android.service.notification.StatusBarNotification
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationDataSource
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import java.util.Collections

@Module
@InstallIn(SingletonComponent::class)
class NotificationDataModule {
    @Provides
    fun provideNotificationCache(): MutableMap<String, StatusBarNotification> {
        return Collections.synchronizedMap(HashMap())
    }

    @Provides
    fun provideNotificationChannel(): BroadcastChannel<Collection<StatusBarNotification>> {
        return BroadcastChannel(Channel.CONFLATED)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class NotificationDataSourceModule {
        @Binds
        abstract fun bindNotificationDataSource(
            source: NotificationDataSourceImpl
        ): NotificationDataSource
    }
}
