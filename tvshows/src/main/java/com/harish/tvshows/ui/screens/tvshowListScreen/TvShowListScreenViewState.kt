package com.harish.tvshows.ui.screens.tvshowListScreen

import com.harish.core.common.ui.base.SideEffect
import com.harish.core.common.ui.base.ViewIntent
import com.harish.core.common.ui.base.ViewState
import com.harish.domain.model.TvShowModel

sealed interface TvShowListScreenViewState : ViewState {

    object Loading : TvShowListScreenViewState
    data class Success(val data: List<TvShowModel>) : TvShowListScreenViewState
    data class Error(val message: String) : TvShowListScreenViewState
}

sealed interface TvShowListScreenIntent : ViewIntent {
    object FetchTvShowList : TvShowListScreenIntent
    data class OnTvShowClicked(val seriesId: Int) : TvShowListScreenIntent

}

sealed interface TvShowListScreenSideEffect:SideEffect{
    data class OnTvShowClicked(val seriesId: Int) : TvShowListScreenIntent

}