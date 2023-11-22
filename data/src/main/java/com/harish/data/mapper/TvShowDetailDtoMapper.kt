package com.harish.data.mapper

import com.harish.data.model.TvShowDetailsDTO
import com.harish.domain.model.TvShowDetailsModel
import javax.inject.Inject

class TvShowDetailDtoMapper @Inject constructor() {
    fun mapFromDTOToDomain(tvShowDetailsModel: TvShowDetailsDTO): TvShowDetailsModel {
        return TvShowDetailsModel(
            backdropPath = tvShowDetailsModel.backdropPath,
            id = tvShowDetailsModel.id,
            name = tvShowDetailsModel.name,
            popularity = tvShowDetailsModel.popularity,
            posterPath = tvShowDetailsModel.posterPath,
            voteAverage = tvShowDetailsModel.voteAverage,
            voteCount = tvShowDetailsModel.voteCount,
            episodeRunTime = tvShowDetailsModel.episodeRunTime,
            languages = tvShowDetailsModel.languages,
            overview = tvShowDetailsModel.overview,
            tagline = tvShowDetailsModel.tagline
        )
    }
}