package com.harish.tvshows.model

data class TvShowListUiModel(
    val tvShowModelList: List<TvShowUiModel>
)

data class TvShowUiModel(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)
