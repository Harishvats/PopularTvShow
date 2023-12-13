package com.harish.tvshows.ui.screens.tvshowListScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenIntent
import com.harish.tvshows.ui.screens.tvshowListScreen.state.TvShowListScreenViewState
import com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel.TvShowListViewModel

@Composable
fun TvShowListScreen(
    viewModel: TvShowListViewModel = hiltViewModel(),
    selectedTvShow: (Int, String) -> Unit
) {

    val result by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.isApiSuccessful, block = {
        viewModel.sendEvent(
            TvShowListScreenIntent.FetchTvShowList
        )
    })

    when (result) {
        is TvShowListScreenViewState.Error -> Toast.makeText(
            context,
            (result as TvShowListScreenViewState.Error).message,
            Toast.LENGTH_SHORT
        ).show()

        is TvShowListScreenViewState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }

        is TvShowListScreenViewState.Success -> {
            val value = (result as TvShowListScreenViewState.Success).data.tvShowModelList
            TvShowGrid(tvShowList = value, selectedTvShow = selectedTvShow)
        }
    }
}
