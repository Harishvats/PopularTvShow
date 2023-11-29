package com.harish.data.repository

import com.harish.data.repository.datasource.RemoteDataSource
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import com.harish.domain.repository.PopularTvShowRepository
import javax.inject.Inject

class PopularTvShowRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    PopularTvShowRepository {
    override suspend fun getPopularTvShows(): Result<TvShowListModel> =
        remoteDataSource.getPopularTvShows()


    override suspend fun getTvShowDetails(seriesId: Int): Result<TvShowDetailsModel> =
        remoteDataSource.getTvShowDetails(seriesId)
}