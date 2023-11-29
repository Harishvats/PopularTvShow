package com.harish.tvshows.ui.screens.tvshowListScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.harish.news.R
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

        is TvShowListScreenViewState.Loading -> Toast.makeText(
            context,
            stringResource(id = R.string.fetching_tvshows),
            Toast.LENGTH_SHORT
        ).show()

        is TvShowListScreenViewState.Success -> {
            val value = (result as TvShowListScreenViewState.Success).data.tvShowModelList
            TvShowGrid(tvShowList = value, selectedTvShow = selectedTvShow)
        }
    }

}