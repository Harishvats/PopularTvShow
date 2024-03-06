package com.harish.tvshows.ui.screens.tvShowDetailsScreen.contract

import com.harish.core.common.ui.base.MVIContract
import com.harish.tvshows.model.TvShowDetailsUiModel


interface TvShowDetailScreenContract :
    MVIContract<TvShowDetailScreenContract.ViewState, TvShowDetailScreenContract.ViewIntent, TvShowDetailScreenContract.SideEffect> {
    sealed interface ViewState {
        object Loading : ViewState
        data class Success(val data: TvShowDetailsUiModel) : ViewState
        data class Error(val message: String) : ViewState
    }

    sealed interface ViewIntent {
        data class FetchTvShowDetails(val seriesId: Int) : ViewIntent
    }

    sealed interface SideEffect

}

