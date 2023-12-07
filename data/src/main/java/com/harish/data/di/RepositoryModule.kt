package com.harish.data.di

import com.harish.data.repository.PopularTvShowRepositoryImpl
import com.harish.domain.repository.PopularTvShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPopularTvShowRepository(
        popularTvShowRepositoryImpl: PopularTvShowRepositoryImpl
    ): PopularTvShowRepository
}
