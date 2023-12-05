package com.harish.tvshows.mapper

import com.harish.domain.model.TvShowDetailsModel
import com.harish.tvshows.model.TvShowDetailsUiModel
import com.harish.tvshows.utils.AppConstants
import javax.inject.Inject

class TvShowDetailDomainToUiMapper @Inject constructor() {
    fun mapFromDomainToUi(tvShowUiModel: TvShowDetailsModel): TvShowDetailsUiModel {
        return TvShowDetailsUiModel(
            backdropPath = AppConstants.IMG_URL_PREFIX + tvShowUiModel.backdropPath,
            id = tvShowUiModel.id,
            name = tvShowUiModel.name,
            popularity = tvShowUiModel.popularity,
            posterPath = AppConstants.IMG_URL_PREFIX + tvShowUiModel.posterPath,
            voteAverage = tvShowUiModel.voteAverage,
            voteCount = tvShowUiModel.voteCount,
            languages = tvShowUiModel.languages,
            episodeRunTime = tvShowUiModel.episodeRunTime,
            tagline = tvShowUiModel.tagline,
            overview = tvShowUiModel.overview
        )
    }

}