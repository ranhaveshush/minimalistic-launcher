package com.ranhaveshush.launcher.minimalistic.di

import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationTransformer
import com.ranhaveshush.launcher.minimalistic.data.notification.NotificationTransformerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class NotificationDataTransformerModule {
    @Binds
    abstract fun bindNotificationTransformer(
        transformer: NotificationTransformerImpl
    ): NotificationTransformer
}
