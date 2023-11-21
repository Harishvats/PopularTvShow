package com.harish.data.repository

import com.harish.core.common.Response
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import com.harish.domain.repository.PopularTvShowRepository

class PopularTvShowRepositoryImpl:PopularTvShowRepository {
    override suspend fun getPopularTvShows(): Response<TvShowListModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getTvShowDetails(seriesId: Int): Response<TvShowDetailsModel> {
        TODO("Not yet implemented")
    }
}