package com.harish.domain.usecases

import com.harish.core.common.Response
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.repository.PopularTvShowRepository
import javax.inject.Inject

class GetTvShowDetailUseCaseImpl @Inject constructor(private val popularTvShowRepository: PopularTvShowRepository) :
    GetTvShowDetailsUseCase {
    override suspend fun invoke(seriesId: Int): Response<TvShowDetailsModel> = popularTvShowRepository.getTvShowDetails(seriesId)
}