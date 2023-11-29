package com.harish.domain.usecases

import com.harish.domain.model.TvShowListModel
import com.harish.domain.repository.PopularTvShowRepository
import javax.inject.Inject

internal class GetPopularTvShowsUseCaseImpl @Inject constructor(private val popularTvShowRepository: PopularTvShowRepository) :
    GetPopularTvShowsUseCase {
    override suspend fun invoke(): Result<TvShowListModel> = popularTvShowRepository.getPopularTvShows()
}