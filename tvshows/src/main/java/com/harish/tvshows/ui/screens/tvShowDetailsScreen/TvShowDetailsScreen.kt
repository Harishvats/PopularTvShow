package com.harish.tvshows.ui.screens.tvShowDetailsScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.harish.tvshows.ui.components.LoadingUI
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.state.TvShowDetailsIntent
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.state.TvShowDetailsScreenState
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.viewmodel.TvShowDetailsViewModel


@Composable
fun TvShowDetailsScreen(
    tvShowDetailsViewModel: TvShowDetailsViewModel,
    selectedTvSeriesID: Int,
) {
    LaunchedEffect(key1 = tvShowDetailsViewModel.isApiCalled, block = {
        if (!tvShowDetailsViewModel.isApiCalled) {
            tvShowDetailsViewModel.sendEvent(
                TvShowDetailsIntent.FetchTvShowDetails(selectedTvSeriesID)
            )
            tvShowDetailsViewModel.isApiCalled = true
        }
    })

    val result by tvShowDetailsViewModel.tvShowDetailsViewStateFlow.collectAsState()
    val context = LocalContext.current
    when (result) {
        is TvShowDetailsScreenState.Loading -> LoadingUI()

        is TvShowDetailsScreenState.Error -> {
            Toast.makeText(
                context,
                (result as TvShowDetailsScreenState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
        }

        is TvShowDetailsScreenState.Success -> {
            TvShowDetailCard(tvShowDetailsModel = (result as TvShowDetailsScreenState.Success).data)
        }
    }

}

