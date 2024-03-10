package com.harish.tvshows.mapper

import com.harish.domain.model.TvShowListModel
import com.harish.domain.model.TvShowModel
import com.harish.tvshows.base.Constants
import com.harish.tvshows.model.TvShowListUiModel
import com.harish.tvshows.model.TvShowUiModel

fun TvShowModel.toUiModel(): TvShowUiModel = TvShowUiModel(
    id = this.id,
    name = this.name,
    posterPath = Constants.IMG_URL_PREFIX + this.posterPath,
    voteCount = this.voteCount
)

fun TvShowListModel.toUiModel() =
    TvShowListUiModel(
        tvShowModelList = this.tvShowModelList.map {
            it.toUiModel()
        }
    )

