package com.harish.domain

import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.model.TvShowListModel
import com.harish.domain.model.TvShowModel

object TestData {
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