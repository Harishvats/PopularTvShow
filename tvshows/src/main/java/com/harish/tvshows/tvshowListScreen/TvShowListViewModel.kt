package com.harish.tvshows.tvshowListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.core.common.Response
import com.harish.domain.usecases.GetPopularTvShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase) :
    ViewModel() {

    private val _tvShowListViewStateFlow =
        MutableStateFlow<TvShowListScreenViewState>(TvShowListScreenViewState.Loading)
    val tvShowListViewStateFlow = _tvShowListViewStateFlow.asStateFlow()

    private val _effect: Channel<TvShowListScreenSideEffect> = Channel()
    val effect = _effect.receiveAsFlow()


    private fun getMovieList() {
        _tvShowListViewStateFlow.value = TvShowListScreenViewState.Loading

        viewModelScope.launch {
            when (val result = getPopularTvShowsUseCase()) {

                is Response.Success -> _tvShowListViewStateFlow.value =
                    TvShowListScreenViewState.Success(result.data.tvShowModelList)

                is Response.Error -> _tvShowListViewStateFlow.value =
                    TvShowListScreenViewState.Error(result.message)

                else -> {}
            }
        }
    }

    fun sendEvent(tvShowListScreenIntent: TvShowListScreenIntent) {
        when (tvShowListScreenIntent) {
            TvShowListScreenIntent.FetchTvShowList -> getMovieList()
            else -> {}
        }

    }
}
