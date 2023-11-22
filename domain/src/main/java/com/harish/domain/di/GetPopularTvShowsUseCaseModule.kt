package com.harish.domain.di

import com.harish.domain.usecases.GetPopularTvShowsUseCase
import com.harish.domain.usecases.GetPopularTvShowsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class GetPopularTvShowsUseCaseModule {

    @Binds
   internal abstract fun bindGetPopularTvShowsUseCase(getPopularTvShowsUseCaseImpl: GetPopularTvShowsUseCaseImpl): GetPopularTvShowsUseCase
}