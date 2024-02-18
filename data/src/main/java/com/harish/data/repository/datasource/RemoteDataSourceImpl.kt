package com.harish.data.repository.datasource

import com.harish.data.BuildConfig
import com.harish.data.api.APIService
import com.harish.data.api.apiCall
import com.harish.data.di.IODispatcher
import com.harish.data.mapper.toDomainModel
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: APIService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {
    override suspend fun getPopularTvShows(): Result<TvShowListModel> {
        return apiCall(apiCall = {
            apiService.getPopularTvShows(BuildConfig.API_KEY)
        }, mapper = { it.toDomainModel() }, ioDispatcher
        )

    }

    override suspend fun getTvShowDetails(seriesId: Int): Result<TvShowDetailsModel> {
        return apiCall(apiCall = {
            apiService.getTvShowDetails(seriesId, BuildConfig.API_KEY)
        }, mapper = { it.toDomainModel() }, ioDispatcher
        )
    }
}

