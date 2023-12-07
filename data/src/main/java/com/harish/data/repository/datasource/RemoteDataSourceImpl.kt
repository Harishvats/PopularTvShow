package com.harish.data.repository.datasource

import com.harish.data.BuildConfig
import com.harish.data.api.APIService
import com.harish.data.mapper.toDomainModel
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import javax.inject.Inject
import okio.IOException
import retrofit2.HttpException

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: APIService
) : RemoteDataSource {
    override suspend fun getPopularTvShows(): Result<TvShowListModel> {
        return try {
            val response = apiService.getPopularTvShows(BuildConfig.API_KEY)
            if (response.isSuccessful) {
                val body = response.body()
                Result.success(body?.toDomainModel()!!)
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
            val response = apiService.getTvShowDetails(seriesId, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                val body = response.body()
                Result.success(body?.toDomainModel()!!)
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

// TODO: Breaking DRY Principle. Create a wrapper class for it.
