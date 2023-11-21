package com.harish.domain.repository

import com.harish.core.common.Response
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel

interface PopularTvShowRepository {
    suspend fun getPopularTvShows(): Response<TvShowListModel>
    suspend fun getTvShowDetails(seriesId:Int):Response<TvShowDetailsModel>
}