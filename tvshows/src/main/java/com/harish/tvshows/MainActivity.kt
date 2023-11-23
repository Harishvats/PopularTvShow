package com.harish.tvshows

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.harish.news.R
import com.harish.tvshows.tvshowListScreen.TvShowListScreenIntent
import com.harish.tvshows.tvshowListScreen.TvShowListScreenViewState
import com.harish.tvshows.tvshowListScreen.TvShowListViewModel
import com.harish.tvshows.ui.theme.TvShowsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            setContent {
                TvShowsAppTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {


                        val tvShowListViewModel: TvShowListViewModel = hiltViewModel()


                        LaunchedEffect(key1 = Unit, block = {
                            tvShowListViewModel.sendEvent(
                                TvShowListScreenIntent.FetchTvShowList
                            )
                        })

                        val result = tvShowListViewModel.tvShowListViewStateFlow.collectAsState()

                        when (result.value) {
                            is TvShowListScreenViewState.Error -> Toast.makeText(
                                applicationContext,
                                (result.value as TvShowListScreenViewState.Error).message,
                                Toast.LENGTH_SHORT
                            ).show()

                            is TvShowListScreenViewState.Loading -> Toast.makeText(
                                applicationContext,
                                stringResource(id = R.string.fetching_tvshows),
                                Toast.LENGTH_SHORT
                            ).show()

                            is TvShowListScreenViewState.Success -> {
                                val value=(result.value as TvShowListScreenViewState.Success).data

                                Log.d(
                                    "MainActivity",
                                    value.toString())
                                Toast.makeText(
                                    applicationContext,value[0].name
                                   ,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        Greeting("Android")
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TvShowsAppTheme {
            Greeting("Android")
        }
    }
}