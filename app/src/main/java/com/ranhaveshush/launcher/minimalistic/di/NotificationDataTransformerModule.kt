package com.ranhaveshush.launcher.minimalistic.di

import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationTransformer
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationTransformerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationDataTransformerModule {
    @Binds
    abstract fun bindNotificationTransformer(
        transformer: NotificationTransformerImpl
    ): NotificationTransformer
}
