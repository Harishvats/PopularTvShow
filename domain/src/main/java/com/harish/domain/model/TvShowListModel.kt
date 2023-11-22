package com.harish.domain.model



data class TvShowListModel(
    val tvShowModelList: List<TvShowModel>
)

data class TvShowModel(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)