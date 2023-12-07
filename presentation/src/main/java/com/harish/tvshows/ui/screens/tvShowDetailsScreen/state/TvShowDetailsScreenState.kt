package com.harish.tvshows.ui.screens.tvShowDetailsScreen.state

import com.harish.core.common.ui.base.ViewIntent
import com.harish.core.common.ui.base.ViewState
import com.harish.tvshows.model.TvShowDetailsUiModel

sealed interface TvShowDetailsScreenState : ViewState {

    object Loading : TvShowDetailsScreenState
    class Success(val data: TvShowDetailsUiModel) : TvShowDetailsScreenState
    class Error(val message: String) : TvShowDetailsScreenState
}

sealed interface TvShowDetailsIntent : ViewIntent {

    class FetchTvShowDetails(val seriesId: Int) : TvShowDetailsIntent
}
