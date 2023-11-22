package com.harish.domain.usecases

import com.harish.core.common.Response
import com.harish.domain.model.TvShowListModel
import com.harish.domain.repository.PopularTvShowRepository
import javax.inject.Inject

internal class GetPopularTvShowsUseCaseImpl @Inject constructor(private val popularTvShowRepository: PopularTvShowRepository) :
    GetPopularTvShowsUseCase {
    override suspend fun invoke(): Response<TvShowListModel> = popularTvShowRepository.getPopularTvShows()
}