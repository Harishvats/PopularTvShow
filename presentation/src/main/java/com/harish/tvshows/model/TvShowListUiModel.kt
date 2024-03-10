package com.harish.tvshows.model

data class TvShowListUiModel(
    val tvShowModelList: List<TvShowUiModel>
)

data class TvShowUiModel(
    val id: Int,
    val name: String,
    val posterPath: String,
    val voteCount: Int,
)
