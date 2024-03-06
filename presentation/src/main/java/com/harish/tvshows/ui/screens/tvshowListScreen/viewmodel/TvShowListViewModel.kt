package com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.domain.usecases.GetPopularTvShowsUseCase
import com.harish.tvshows.mapper.toUiModel
import com.harish.tvshows.ui.screens.tvshowListScreen.contract.TvShowListScreenContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val useCase: GetPopularTvShowsUseCase
) : ViewModel(), TvShowListScreenContract {

    override fun createInitialState(): TvShowListScreenContract.ViewState =
        TvShowListScreenContract.ViewState.Loading

    private val _state = MutableStateFlow(value = createInitialState())
    private val _sideEffect = MutableSharedFlow<TvShowListScreenContract.SideEffect>()

    override val viewState: StateFlow<TvShowListScreenContract.ViewState>
        get() = _state.asStateFlow()
    override val sideEffect: SharedFlow<TvShowListScreenContract.SideEffect>
        get() = _sideEffect.asSharedFlow()

    override fun sendEvent(viewIntent: TvShowListScreenContract.ViewIntent) {

        when (viewIntent) {
            is TvShowListScreenContract.ViewIntent.FetchTvShowList -> getTvShowList()
            is TvShowListScreenContract.ViewIntent.OnTvShowClicked -> {
                viewModelScope.launch {
                    _sideEffect.emit(
                        TvShowListScreenContract.SideEffect.NavigateToDetailsScreen(
                            viewIntent.seriesId,
                            viewIntent.showName
                        )
                    )
                }

            }
        }
    }

    private fun getTvShowList() {
        viewModelScope.launch {
            _state.value = TvShowListScreenContract.ViewState.Loading

            useCase().onSuccess {
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
