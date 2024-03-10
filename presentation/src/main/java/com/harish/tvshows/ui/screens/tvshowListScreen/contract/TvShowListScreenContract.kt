package com.harish.tvshows.ui.screens.tvshowListScreen.contract

import com.harish.tvshows.base.MVIContract
import com.harish.tvshows.model.TvShowListUiModel


interface TvShowListScreenContract :
    MVIContract<TvShowListScreenContract.ViewState, TvShowListScreenContract.ViewIntent, TvShowListScreenContract.SideEffect> {
    sealed interface ViewState {
        object Loading : ViewState
        data class Success(val data: TvShowListUiModel) : ViewState
        data class Error(val message: String) : ViewState
    }

    sealed interface ViewIntent {
        object FetchTvShowList : ViewIntent
        data class OnTvShowClicked(val seriesId: Int, val showName: String) : ViewIntent
    }

    sealed interface SideEffect {
        data class NavigateToDetailsScreen(val seriesId: Int, val showName: String) : SideEffect
    }
}

