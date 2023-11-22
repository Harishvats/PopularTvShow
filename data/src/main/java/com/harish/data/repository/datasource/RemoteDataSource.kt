package com.harish.data.repository.datasource

import com.harish.core.common.Response
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel

interface RemoteDataSource {
    suspend fun getPopularTvShows(): Response<TvShowListModel>
    suspend fun getTvShowDetails(seriesId: Int): Response<TvShowDetailsModel>

}