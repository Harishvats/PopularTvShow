package com.harish.domain.usecases

import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.repository.PopularTvShowRepository
import javax.inject.Inject

internal class GetTvShowDetailUseCaseImpl @Inject constructor(private val popularTvShowRepository: PopularTvShowRepository) :
    GetTvShowDetailsUseCase {
    override suspend fun invoke(seriesId: Int): Result<TvShowDetailsModel> = popularTvShowRepository.getTvShowDetails(seriesId)
}