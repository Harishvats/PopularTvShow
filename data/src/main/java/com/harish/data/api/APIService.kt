package com.harish.data.api

import com.harish.data.model.TvShowDetailsDTO
import com.harish.data.model.TvShowListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apikey: String): TvShowListDTO

    @GET("tv/{series_id}")
    suspend fun getTvShowDetails(@Path("series_id") seriesId: Int, @Query("api_key") apikey: String): TvShowDetailsDTO
}
