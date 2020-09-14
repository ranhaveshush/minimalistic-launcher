package com.ranhaveshush.launcher.minimalistic.di

import com.ranhaveshush.launcher.minimalistic.data.app.DrawerAppTransformer
import com.ranhaveshush.launcher.minimalistic.data.app.DrawerAppTransformerImpl
import com.ranhaveshush.launcher.minimalistic.data.app.HomeAppTransformer
import com.ranhaveshush.launcher.minimalistic.data.app.HomeAppTransformerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppDataTransformerModule {
    @Binds
    abstract fun bindHomeAppTransformer(
        transformer: HomeAppTransformerImpl
    ): HomeAppTransformer

    @Binds
    abstract fun bindDrawerAppTransformer(
        transformer: DrawerAppTransformerImpl
    ): DrawerAppTransformer
}
