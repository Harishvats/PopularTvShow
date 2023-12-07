package com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel

import androidx.lifecycle.viewModelScope
import com.harish.core.common.ui.base.BaseViewModel
import com.harish.core.common.ui.base.ViewIntent
import com.harish.domain.usecases.GetPopularTvShowsUseCase
import com.harish.tvshows.mapper.toUiModel
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenIntent
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val useCase: GetPopularTvShowsUseCase
) : BaseViewModel() {

    private val _state =
        MutableStateFlow<TvShowListScreenViewState>(TvShowListScreenViewState.Loading)
    val state = _state.asStateFlow()

    var isApiSuccessful: Boolean = false

    override fun sendEvent(viewIntent: ViewIntent) {
        if (viewIntent is TvShowListScreenIntent.FetchTvShowList) {
            getTvShowList()
        }
    }

    private fun getTvShowList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = TvShowListScreenViewState.Loading

            useCase().onSuccess {
                isApiSuccessful = true
                _state.value = TvShowListScreenViewState.Success(
                    it.toUiModel()
                )
            }.onFailure {
                _state.value =
                    TvShowListScreenViewState.Error(it.message ?: "")
            }
        }
    }
}
