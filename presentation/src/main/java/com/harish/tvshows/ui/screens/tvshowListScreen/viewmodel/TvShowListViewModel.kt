package com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.domain.usecases.GetPopularTvShowsUseCase
import com.harish.tvshows.mapper.toUiModel
import com.harish.tvshows.ui.screens.tvshowListScreen.contract.TvShowListScreenContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val useCase: GetPopularTvShowsUseCase
) : ViewModel(), TvShowListScreenContract {

    override fun createInitialState(): TvShowListScreenContract.ViewState =
        TvShowListScreenContract.ViewState.Loading

    private val _state = MutableStateFlow(value = createInitialState())
    private val _sideEffect = Channel<TvShowListScreenContract.SideEffect>()

    override val viewState: StateFlow<TvShowListScreenContract.ViewState>
        get() = _state.asStateFlow()
    override val sideEffect: Flow<TvShowListScreenContract.SideEffect>
        get() = _sideEffect.receiveAsFlow()


    var isApiSuccessful: Boolean = false

    override fun sendEvent(viewIntent: TvShowListScreenContract.ViewIntent) {
        if (viewIntent is TvShowListScreenContract.ViewIntent.FetchTvShowList) {
            getTvShowList()
        }
        if (viewIntent is TvShowListScreenContract.ViewIntent.OnTvShowClicked) {
            viewModelScope.launch {
                _sideEffect.send(
                    TvShowListScreenContract.SideEffect.NavigateToDetailsScreen(
                        viewIntent.seriesId,
                        viewIntent.showName
                    )
                )
            }
        }
    }

    private fun getTvShowList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = TvShowListScreenContract.ViewState.Loading

            useCase().onSuccess {
                isApiSuccessful = true
                _state.value = TvShowListScreenContract.ViewState.Success(
                    it.toUiModel()
                )
            }.onFailure {
                _state.value =
                    TvShowListScreenContract.ViewState.Error(it.message ?: "")
            }
        }
    }


}
