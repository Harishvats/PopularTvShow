package com.harish.data.repository.datasource

import com.harish.data.BuildConfig
import com.harish.data.api.NetworkService
import com.harish.data.mapper.TvShowDetailDtoMapper
import com.harish.data.mapper.TvShowListDTOMapper
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val networkService: NetworkService,
    private val tvShowListDTOMapper: TvShowListDTOMapper,
    private val tvShowDetailDtoMapper: TvShowDetailDtoMapper
) : RemoteDataSource {
    override suspend fun getPopularTvShows(): Result<TvShowListModel> {
        return try {
            val response = networkService.getPopularTvShows(BuildConfig.API_KEY)
            if (response.isSuccessful) {

                val body = response.body()
                Result.success(body?.let { tvShowListDTOMapper.mapFromDTOToDomain(it) }!!)
            } else {
                Result.failure(Throwable(response.message()))
            }
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IOException) {
            Result.failure(e)
        }

    }

    override suspend fun getTvShowDetails(seriesId: Int): Result<TvShowDetailsModel> {
        return try {
            val response = networkService.getTvShowDetails(seriesId, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                val body = response.body()
                Result.success(body?.let { tvShowDetailDtoMapper.mapFromDTOToDomain(it) }!!)
            } else {
                Result.failure(Throwable(response.message()))
            }
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}