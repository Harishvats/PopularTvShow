package com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel

import androidx.lifecycle.viewModelScope
import com.harish.core.common.ui.base.BaseViewModel
import com.harish.core.common.ui.base.ViewIntent
import com.harish.domain.usecases.GetPopularTvShowsUseCase
import com.harish.tvshows.mapper.TvShowListDomainToUiMapper
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenIntent
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val tvShowListDomainToUiMapper: TvShowListDomainToUiMapper
) : BaseViewModel() {

    private val _tvShowListViewStateFlow =
        MutableStateFlow<TvShowListScreenViewState>(TvShowListScreenViewState.Loading)
    val tvShowListViewStateFlow = _tvShowListViewStateFlow.asStateFlow()

    var isApiCalled: Boolean = false
    private fun getTvShowList() {

        viewModelScope.launch(Dispatchers.IO) {
            _tvShowListViewStateFlow.value = TvShowListScreenViewState.Loading

            getPopularTvShowsUseCase().onSuccess {
                _tvShowListViewStateFlow.value = TvShowListScreenViewState.Success(
                    tvShowListDomainToUiMapper.mapFromDomainToUi(
                        it
                    )
                )

            }.onFailure {
                _tvShowListViewStateFlow.value = TvShowListScreenViewState.Error(it.message ?: "")
            }
        }
    }


    override fun sendEvent(viewIntent: ViewIntent) {
        when (viewIntent) {
            TvShowListScreenIntent.FetchTvShowList -> getTvShowList()
            else -> {}
        }

    }
}
