package com.harish.tvshows.ui.screens.tvShowDetailsScreen

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
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.state.TvShowDetailsIntent
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.state.TvShowDetailsScreenState
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.viewmodel.TvShowDetailsViewModel

@Composable
fun TvShowDetailsScreen(
    viewModel: TvShowDetailsViewModel = hiltViewModel(),
    selectedTvSeriesID: Int
) {
    val context = LocalContext.current
    val result by viewModel.state.collectAsState()

    LaunchedEffect(
        key1 = viewModel.isApiSuccessful,
        block = {
            viewModel.sendEvent(
                TvShowDetailsIntent.FetchTvShowDetails(selectedTvSeriesID)
            )
        }
    )

    when (result) {
        is TvShowDetailsScreenState.Loading ->{
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

        is TvShowDetailsScreenState.Error -> {
            Toast.makeText(
                context,
                (result as TvShowDetailsScreenState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
        }

        is TvShowDetailsScreenState.Success -> {
            TvShowDetailCard(model = (result as TvShowDetailsScreenState.Success).data)
        }
    }
}
