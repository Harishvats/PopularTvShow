package com.harish.domain.di

import com.harish.domain.usecases.GetTvShowDetailUseCaseImpl
import com.harish.domain.usecases.GetTvShowDetailsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class GetTvShowDetailsUseCaseModule {

    @Binds
    internal abstract fun bindGetTvShowDetailsUseCaseModule(getTvShowDetailUseCaseImpl: GetTvShowDetailUseCaseImpl): GetTvShowDetailsUseCase
}