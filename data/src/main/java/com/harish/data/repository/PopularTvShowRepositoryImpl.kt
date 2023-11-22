package com.harish.data.repository

import com.harish.core.common.Response
import com.harish.data.repository.datasource.RemoteDataSource
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import com.harish.domain.repository.PopularTvShowRepository
import javax.inject.Inject

class PopularTvShowRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    PopularTvShowRepository {
    override suspend fun getPopularTvShows(): Response<TvShowListModel> =
        remoteDataSource.getPopularTvShows()


    override suspend fun getTvShowDetails(seriesId: Int): Response<TvShowDetailsModel> =
        remoteDataSource.getTvShowDetails(seriesId)
}