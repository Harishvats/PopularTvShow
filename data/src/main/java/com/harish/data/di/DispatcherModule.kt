package com.harish.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@InstallIn(ViewModelComponent::class)
@Module
object DispatcherModule {
    @IODispatcher
    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher
