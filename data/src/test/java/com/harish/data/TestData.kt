package com.harish.data

import com.harish.data.model.TvShowDTO
import com.harish.data.model.TvShowDetailsDTO
import com.harish.data.model.TvShowListDTO
import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import com.harish.domain.model.TvShowModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object TestData {
    val listErrorResponse: Response<TvShowListDTO> = Response.error(
        404, "".toResponseBody("application/json".toMediaTypeOrNull())
    )
    const val IOResponseErrorMessage = "IO Error"
    val detailsErrorResponse: Response<TvShowDetailsDTO> = Response.error(
        404, "".toResponseBody("application/json".toMediaTypeOrNull())
    )

    val tvShowDTO = TvShowDTO(
        adult = false,
        backdropPath = "",
        firstAirDate = "20Dec1991",
        genreIds = listOf(),
        id = 100,
        name = "Doremon",
        overview = "Good kid series",
        voteCount = 500,
        voteAverage = 883.5,
        posterPath = "",
        originalLanguage = "en",
        originalName = "Doremon",
        originCountry = listOf("Japan"),
        popularity = 220.9
    )

    val tvShowListDTO = TvShowListDTO(tvShowDTOS = listOf(tvShowDTO))

    val tvShowModel = TvShowModel(
        backdropPath = "",
        id = 100,
        name = "Doremon",
        voteCount = 500,
        voteAverage = 883.5,
        posterPath = "",
        popularity = 220.9
    )

    val tvShowListModel = TvShowListModel(tvShowModelList = listOf(tvShowModel))


    val tvShowDetailsDTO = TvShowDetailsDTO(
        adult = false,
        backdropPath = "",
        firstAirDate = "20Dec1991",
        id = 100,
        name = "Doremon",
        overview = "Good kid series",
        voteCount = 500,
        voteAverage = 883.5,
        posterPath = "",
        originalLanguage = "en",
        originalName = "Doremon",
        originCountry = listOf("Japan"),
        popularity = 220.9,
        tagline = "",
        languages = listOf("en"),
        episodeRunTime = listOf(),
        homepage = "homepage",
        inProduction = true,
        lastAirDate = "202033",
        numberOfEpisodes = 15, numberOfSeasons = 3,
        type = "type",
        status = "status",
    )

    val tvShowDetailsModel = TvShowDetailsModel(
        backdropPath = "",
        id = 100,
        name = "Doremon",
        overview = "Good kid series",
        voteCount = 500,
        voteAverage = 883.5,
        posterPath = "",
        popularity = 220.9,
        tagline = "",
        languages = listOf("en"),
        episodeRunTime = listOf(),
    )

}