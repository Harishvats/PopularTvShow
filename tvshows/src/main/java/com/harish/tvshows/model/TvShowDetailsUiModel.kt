package com.harish.tvshows.model
data class TvShowDetailsUiModel(
    val backdropPath: String,
    val episodeRunTime: List<Int>,
    val id: Int,
    val languages: List<String>,
    val name: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val tagline: String,
    val voteAverage: Double,
    val voteCount: Int
)

