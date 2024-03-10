package com.harish.data.mapper

import com.harish.data.model.TvShowDetailsDTO
import com.harish.domain.model.TvShowDetailsModel


fun TvShowDetailsDTO.toDomainModel() = TvShowDetailsModel(
    backdropPath = this.backdropPath,
    id = this.id,
    name = this.name,
    popularity = this.popularity,
    posterPath = this.posterPath,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    episodeRunTime = this.episodeRunTime,
    languages = this.languages,
    overview = this.overview,
    tagline = this.tagline
)
