package com.harish.tvshows.mapper

import com.harish.domain.model.TvShowDetailsModel
import com.harish.tvshows.base.Constants
import com.harish.tvshows.model.TvShowDetailsUiModel

fun TvShowDetailsModel.toUiModel() = TvShowDetailsUiModel(
    backdropPath = Constants.IMG_URL_PREFIX + this.backdropPath,
    id = this.id,
    name = this.name,
    tagline = this.tagline,
    overview = this.overview
)
