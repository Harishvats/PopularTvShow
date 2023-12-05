package com.harish.tvshows.ui.screens.tvshowListScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.harish.tvshows.ui.components.LoadingUI
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenIntent
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenViewState
import com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel.TvShowListViewModel

@Composable
fun TvShowListScreen(
    tvShowListViewModel: TvShowListViewModel,
    selectedTvShow: (Int, String) -> Unit
) {

    LaunchedEffect(key1 = tvShowListViewModel.isApiCalled, block = {
        if (!tvShowListViewModel.isApiCalled) {
            tvShowListViewModel.sendEvent(
                TvShowListScreenIntent.FetchTvShowList
            )
            tvShowListViewModel.isApiCalled = true
        }
    })


    val result by tvShowListViewModel.tvShowListViewStateFlow.collectAsState()
    val context = LocalContext.current
    when (result) {
        is TvShowListScreenViewState.Error -> Toast.makeText(
            context,
            (result as TvShowListScreenViewState.Error).message,
            Toast.LENGTH_SHORT
        ).show()

        is TvShowListScreenViewState.Loading -> LoadingUI()

        is TvShowListScreenViewState.Success -> {
            val value = (result as TvShowListScreenViewState.Success).data.tvShowModelList
            TvShowGrid(tvShowList = value, selectedTvShow = selectedTvShow)
        }
    }

}