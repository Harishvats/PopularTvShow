package com.harish.tvshows.mapper

import com.harish.domain.model.TvShowListModel
import com.harish.domain.model.TvShowModel
import com.harish.tvshows.model.TvShowListUiModel
import com.harish.tvshows.model.TvShowUiModel
import com.harish.tvshows.utils.AppConstants
import javax.inject.Inject

class TvShowListDomainToUiMapper @Inject constructor(private val tvShowDomainToUiMapper: TvShowDomainToUiMapper) {

    fun mapFromDomainToUi(tvShowListModel: TvShowListModel): TvShowListUiModel{
        return TvShowListUiModel(tvShowModelList = tvShowListModel.tvShowModelList.map {
            tvShowDomainToUiMapper.mapFromDomainToUi(
                it
            )
        })
    }


}


class TvShowDomainToUiMapper @Inject constructor() {
    fun mapFromDomainToUi(tvShowModel: TvShowModel): TvShowUiModel {
        return TvShowUiModel(
            backdropPath = AppConstants.IMG_URL_PREFIX+tvShowModel.backdropPath,
            id = tvShowModel.id,
            name = tvShowModel.name,
            popularity = tvShowModel.popularity,
            posterPath = AppConstants.IMG_URL_PREFIX+tvShowModel.posterPath,
            voteAverage = tvShowModel.voteAverage,
            voteCount = tvShowModel.voteCount
        )
    }
}