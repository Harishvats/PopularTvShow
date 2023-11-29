package com.harish.domain.repository

import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel

interface PopularTvShowRepository {
    suspend fun getPopularTvShows(): Result<TvShowListModel>
    suspend fun getTvShowDetails(seriesId:Int):Result<TvShowDetailsModel>
}