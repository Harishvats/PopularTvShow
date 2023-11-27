package com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.core.common.Response
import com.harish.domain.usecases.GetPopularTvShowsUseCase
import com.harish.tvshows.mapper.TvShowListDomainToUiMapper
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenIntent
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenSideEffect
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val tvShowListDomainToUiMapper: TvShowListDomainToUiMapper
) :
    ViewModel() {

    private val _tvShowListViewStateFlow =
        MutableStateFlow<TvShowListScreenViewState>(TvShowListScreenViewState.Loading)
    val tvShowListViewStateFlow = _tvShowListViewStateFlow.asStateFlow()

    private val _effect: Channel<TvShowListScreenSideEffect> = Channel()
    val effect = _effect.receiveAsFlow()


    private fun getTvShowList() {
        _tvShowListViewStateFlow.value = TvShowListScreenViewState.Loading

        viewModelScope.launch {
            when (val result = getPopularTvShowsUseCase()) {

                is Response.Success -> _tvShowListViewStateFlow.value =
                    TvShowListScreenViewState.Success(tvShowListDomainToUiMapper.mapFromDomainToUi(result.data))

                is Response.Error -> _tvShowListViewStateFlow.value =
                    TvShowListScreenViewState.Error(result.message)

                else -> {}
            }
        }
    }

    fun sendEvent(tvShowListScreenIntent: TvShowListScreenIntent) {
        when (tvShowListScreenIntent) {
            TvShowListScreenIntent.FetchTvShowList -> getTvShowList()
            else -> {}
        }

    }
}
