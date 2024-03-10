package com.harish.data.mapper

import com.harish.data.model.TvShowDTO
import com.harish.data.model.TvShowListDTO
import com.harish.domain.model.TvShowListModel
import com.harish.domain.model.TvShowModel

fun TvShowListDTO.toDomainModel() = TvShowListModel(
    tvShowModelList = this.tvShowDTOS.map {
        it.toDomainModel()
    }
)

fun TvShowDTO.toDomainModel() = TvShowModel(
    backdropPath = this.backdropPath,
    id = this.id,
    name = this.name,
    popularity = this.popularity,
    posterPath = this.posterPath,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

