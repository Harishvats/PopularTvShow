package com.harish.data.repository.datasource

import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel

interface RemoteDataSource {
    suspend fun getPopularTvShows(): Result<TvShowListModel>
    suspend fun getTvShowDetails(seriesId: Int): Result<TvShowDetailsModel>
}