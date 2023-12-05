package com.harish.tvshows.ui.screens.tvShowDetailsScreen.viewmodel

import androidx.lifecycle.viewModelScope
import com.harish.core.common.ui.base.BaseViewModel
import com.harish.core.common.ui.base.ViewIntent
import com.harish.domain.usecases.GetTvShowDetailsUseCase
import com.harish.tvshows.mapper.TvShowDetailDomainToUiMapper
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.state.TvShowDetailsIntent
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.state.TvShowDetailsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCase,
    private val tvShowDetailDomainToUiMapper: TvShowDetailDomainToUiMapper
) : BaseViewModel() {
    private val _tvShowDetailsViewStateFlow =
        MutableStateFlow<TvShowDetailsScreenState>(TvShowDetailsScreenState.Loading)
    val tvShowDetailsViewStateFlow = _tvShowDetailsViewStateFlow.asStateFlow()

    var isApiCalled: Boolean = false


    private fun fetchTvShowDetails(seriesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _tvShowDetailsViewStateFlow.value = TvShowDetailsScreenState.Loading
            getTvShowDetailsUseCase(seriesId).onSuccess {
                _tvShowDetailsViewStateFlow.value = TvShowDetailsScreenState.Success(
                    tvShowDetailDomainToUiMapper.mapFromDomainToUi(it)
                )
            }.onFailure {
                _tvShowDetailsViewStateFlow.value = TvShowDetailsScreenState.Error(it.message ?: "")
            }
        }
    }

    override fun sendEvent(viewIntent: ViewIntent) {
        when (viewIntent) {
            is TvShowDetailsIntent.FetchTvShowDetails -> fetchTvShowDetails(viewIntent.seriesId)
        }
    }

}