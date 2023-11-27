package com.harish.tvshows.ui.screens.tvshowListScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.harish.news.R

@Composable
fun TvShowListScreen(
    tvShowListViewModel: TvShowListViewModel,
    selectedTvShow: (Int, String) -> Unit
) {
    LaunchedEffect(key1 = Unit, block = {
        tvShowListViewModel.sendEvent(
            TvShowListScreenIntent.FetchTvShowList
        )
    })

    val result = tvShowListViewModel.tvShowListViewStateFlow.collectAsState()
    val context = LocalContext.current
    when (result.value) {
        is TvShowListScreenViewState.Error -> Toast.makeText(
            context,
            (result.value as TvShowListScreenViewState.Error).message,
            Toast.LENGTH_SHORT
        ).show()

        is TvShowListScreenViewState.Loading -> Toast.makeText(
            context,
            stringResource(id = R.string.fetching_tvshows),
            Toast.LENGTH_SHORT
        ).show()

        is TvShowListScreenViewState.Success -> {
            val value = (result.value as TvShowListScreenViewState.Success).data
            TvShowGrid(tvShowList = value, selectedTvShow = selectedTvShow)
            Log.d(
                "MainActivity",
                value.toString()
            )
            Toast.makeText(
                context, value[0].name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}