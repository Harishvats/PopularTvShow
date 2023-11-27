package com.harish.tvshows.ui.screens.tvshowListScreen.state

import com.harish.core.common.ui.base.SideEffect
import com.harish.core.common.ui.base.ViewIntent
import com.harish.core.common.ui.base.ViewState
import com.harish.tvshows.model.TvShowListUiModel

sealed interface TvShowListScreenViewState : ViewState {

    object Loading : TvShowListScreenViewState
    data class Success(val data: TvShowListUiModel) : TvShowListScreenViewState
    data class Error(val message: String) : TvShowListScreenViewState
}

sealed interface TvShowListScreenIntent : ViewIntent {
    object FetchTvShowList : TvShowListScreenIntent
    data class OnTvShowClicked(val seriesId: Int, val showName: String) : TvShowListScreenIntent

}

sealed interface TvShowListScreenSideEffect : SideEffect {
    data class OnTvShowClicked(val seriesId: Int, val showName: String) : TvShowListScreenIntent

}