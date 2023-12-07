package com.harish.tvshows.ui.screens.tvShowDetailsScreen.viewmodel

import androidx.lifecycle.viewModelScope
import com.harish.core.common.ui.base.BaseViewModel
import com.harish.core.common.ui.base.ViewIntent
import com.harish.domain.usecases.GetTvShowDetailsUseCase
import com.harish.tvshows.mapper.toUiModel
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.state.TvShowDetailsIntent
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.state.TvShowDetailsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val useCase: GetTvShowDetailsUseCase
) : BaseViewModel() {
    private val _state =
        MutableStateFlow<TvShowDetailsScreenState>(TvShowDetailsScreenState.Loading)
    val state = _state.asStateFlow()

    var isApiSuccessful: Boolean = false

    override fun sendEvent(viewIntent: ViewIntent) {
        when (viewIntent) {
            is TvShowDetailsIntent.FetchTvShowDetails -> fetchTvShowDetails(viewIntent.seriesId)
        }
    }

    private fun fetchTvShowDetails(seriesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = TvShowDetailsScreenState.Loading
            useCase(seriesId).onSuccess {
                isApiSuccessful = true
                _state.value = TvShowDetailsScreenState.Success(
                    it.toUiModel()
                )
            }.onFailure {
                _state.value =
                    TvShowDetailsScreenState.Error(it.message ?: "")
            }
        }
    }
}
