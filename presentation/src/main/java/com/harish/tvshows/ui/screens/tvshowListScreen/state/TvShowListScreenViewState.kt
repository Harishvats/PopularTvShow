package com.harish.tvshows.ui.screens.tvshowListScreen.state

import com.harish.core.common.ui.base.SideEffect
import com.harish.core.common.ui.base.ViewIntent
import com.harish.core.common.ui.base.ViewState
import com.harish.tvshows.model.TvShowListUiModel

sealed interface TvShowListScreenViewState : ViewState {

    object Loading : TvShowListScreenViewState
    class Success(val data: TvShowListUiModel) : TvShowListScreenViewState
    class Error(val message: String) : TvShowListScreenViewState
}

sealed interface TvShowListScreenIntent : ViewIntent {
    object FetchTvShowList : TvShowListScreenIntent
    class OnTvShowClicked(val seriesId: Int, val showName: String) : TvShowListScreenIntent
}

sealed interface TvShowListScreenSideEffect : SideEffect {
    class OnTvShowClicked(val seriesId: Int, val showName: String) : TvShowListScreenIntent
}
