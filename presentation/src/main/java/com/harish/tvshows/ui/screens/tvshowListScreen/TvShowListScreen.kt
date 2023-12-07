package com.harish.tvshows.ui.screens.tvshowListScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.harish.tvshows.ui.components.LoadingUI
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenIntent
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenViewState
import com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel.TvShowListViewModel

@Composable
fun TvShowListScreen(
    viewModel: TvShowListViewModel = hiltViewModel(),
    selectedTvShow: (Int, String) -> Unit
) {
    LaunchedEffect(key1 = viewModel.isApiSuccessful, block = {
        viewModel.sendEvent(
            TvShowListScreenIntent.FetchTvShowList
        )
    })

    val result by viewModel.state.collectAsState()
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
