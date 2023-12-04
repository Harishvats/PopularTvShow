package com.harish.data.mapper

import com.harish.data.model.TvShowDetailsDTO
import com.harish.domain.model.TvShowDetailsModel
import javax.inject.Inject

class TvShowDetailDtoMapper @Inject constructor() {
    fun mapFromDTOToDomain(tvShowDetailsDTO: TvShowDetailsDTO): TvShowDetailsModel {
        return TvShowDetailsModel(
            backdropPath = tvShowDetailsDTO.backdropPath,
            id = tvShowDetailsDTO.id,
            name = tvShowDetailsDTO.name,
            popularity = tvShowDetailsDTO.popularity,
            posterPath = tvShowDetailsDTO.posterPath,
            voteAverage = tvShowDetailsDTO.voteAverage,
            voteCount = tvShowDetailsDTO.voteCount,
            episodeRunTime = tvShowDetailsDTO.episodeRunTime,
            languages = tvShowDetailsDTO.languages,
            overview = tvShowDetailsDTO.overview,
            tagline = tvShowDetailsDTO.tagline
        )
    }
}