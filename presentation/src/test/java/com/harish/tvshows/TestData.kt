package com.harish.tvshows

import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import com.harish.domain.model.TvShowModel
import com.harish.tvshows.model.TvShowDetailsUiModel
import com.harish.tvshows.model.TvShowListUiModel
import com.harish.tvshows.model.TvShowUiModel

object TestData {
    val tvShowModel = TvShowModel(
        backdropPath = "/backdropPath",
        id = 100,
        name = "Doremon",
        voteCount = 500,
        voteAverage = 883.5,
        posterPath = "/posterPath",
        popularity = 220.9
    )
    val tvShowUiModel = TvShowUiModel(
        id = 100,
        name = "Doremon",
        voteCount = 500,
        posterPath = "https://image.tmdb.org/t/p/w500/posterPath",
    )

    val tvShowListModel = TvShowListModel(tvShowModelList = listOf(tvShowModel))
    val tvShowListUiModel = TvShowListUiModel(tvShowModelList = listOf(tvShowUiModel))

    val tvShowDetailsModel = TvShowDetailsModel(
        backdropPath = "/backdropPath",
        id = 100,
        name = "Doremon",
        overview = "Good kid series",
        voteCount = 500,
        voteAverage = 883.5,
        posterPath = "/posterPath",
        popularity = 220.9,
        tagline = "",
        languages = listOf("en"),
        episodeRunTime = listOf(),
    )

    val tvShowDetailsUiModel = TvShowDetailsUiModel(
        backdropPath = "https://image.tmdb.org/t/p/w500/backdropPath",
        id = 100,
        name = "Doremon",
        overview = "Good kid series",
        tagline = "",
    )

    const val errorString="Something went wrong"


}