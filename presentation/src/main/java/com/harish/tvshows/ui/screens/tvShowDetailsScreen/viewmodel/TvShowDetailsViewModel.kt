package com.harish.tvshows.ui.screens.tvShowDetailsScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.domain.usecases.GetTvShowDetailsUseCase
import com.harish.tvshows.mapper.toUiModel
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.contract.TvShowDetailScreenContract
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
class TvShowDetailsViewModel @Inject constructor(
    private val useCase: GetTvShowDetailsUseCase,
) : ViewModel(), TvShowDetailScreenContract {

    override fun createInitialState(): TvShowDetailScreenContract.ViewState =
        TvShowDetailScreenContract.ViewState.Loading

    private val _state = MutableStateFlow(value = createInitialState())
    private val _sideEffect = MutableSharedFlow<TvShowDetailScreenContract.SideEffect>()

    override val viewState: StateFlow<TvShowDetailScreenContract.ViewState>
        get() = _state.asStateFlow()
    override val sideEffect: SharedFlow<TvShowDetailScreenContract.SideEffect>
        get() = _sideEffect.asSharedFlow()



    override fun sendEvent(viewIntent: TvShowDetailScreenContract.ViewIntent) {
        if (viewIntent is TvShowDetailScreenContract.ViewIntent.FetchTvShowDetails) {
            fetchTvShowDetails(viewIntent.seriesId)
        }
    }


    private fun fetchTvShowDetails(seriesId: Int) {
        viewModelScope.launch {
            _state.value = TvShowDetailScreenContract.ViewState.Loading
            useCase(seriesId).onSuccess {
                _state.value = TvShowDetailScreenContract.ViewState.Success(
                    it.toUiModel()
                )
            }.onFailure {
                _state.value = TvShowDetailScreenContract.ViewState.Error(it.message ?: "")
            }
        }
    }
}
