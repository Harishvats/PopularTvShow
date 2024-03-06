package com.harish.tvshows.ui.screens.tvshowListScreen

import android.util.Log
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
import com.harish.tvshows.ui.screens.tvshowListScreen.contract.TvShowListScreenContract
import com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel.TvShowListViewModel

@Composable
fun TvShowListScreen(
    viewModel: TvShowListViewModel = hiltViewModel(), selectedTvShow: (Int, String) -> Unit
) {

    val result by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        viewModel.sendEvent(
            TvShowListScreenContract.ViewIntent.FetchTvShowList
        )

        viewModel.sideEffect.collect {
            if (it is TvShowListScreenContract.SideEffect.NavigateToDetailsScreen) {
                selectedTvShow(it.seriesId, it.showName)
            }
        }
        })


    when (result) {

        is TvShowListScreenContract.ViewState.Error -> {
            Toast.makeText(
                context,
                (result as TvShowListScreenContract.ViewState.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            Log.d("Harish", (result as TvShowListScreenContract.ViewState.Error).message)
        }

        is TvShowListScreenContract.ViewState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }

        is TvShowListScreenContract.ViewState.Success -> {
            val value = (result as TvShowListScreenContract.ViewState.Success).data.tvShowModelList
            TvShowGrid(tvShowList = value, tvShowClicked = { seriesID, showName ->
                viewModel.sendEvent(
                    TvShowListScreenContract.ViewIntent.OnTvShowClicked(
                        seriesID, showName
                    )
                )

            })
        }
    }
}
