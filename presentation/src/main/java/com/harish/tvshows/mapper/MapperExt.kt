package com.harish.tvshows.mapper

import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import com.harish.domain.model.TvShowModel
import com.harish.tvshows.model.TvShowDetailsUiModel
import com.harish.tvshows.model.TvShowListUiModel
import com.harish.tvshows.model.TvShowUiModel
import com.harish.tvshows.utils.AppConstants

fun TvShowModel.toUiModel(): TvShowUiModel = TvShowUiModel(
    backdropPath = AppConstants.IMG_URL_PREFIX + this.backdropPath,
    id = this.id,
    name = this.name,
    popularity = this.popularity,
    posterPath = AppConstants.IMG_URL_PREFIX + this.posterPath,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun TvShowListModel.toUiModel() =
    TvShowListUiModel(
        tvShowModelList = this.tvShowModelList.map {
            it.toUiModel()
        }
    )

fun TvShowDetailsModel.toUiModel() = TvShowDetailsUiModel(
    backdropPath = AppConstants.IMG_URL_PREFIX + this.backdropPath,
    id = this.id,
    name = this.name,
    popularity = this.popularity,
    posterPath = AppConstants.IMG_URL_PREFIX + this.posterPath,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    languages = this.languages,
    episodeRunTime = this.episodeRunTime,
    tagline = this.tagline,
    overview = this.overview
)
