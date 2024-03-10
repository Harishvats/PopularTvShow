package com.harish.tvshows.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.harish.news.R
import com.harish.tvshows.ui.components.BaseScreen
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.TvShowDetailsScreen
import com.harish.tvshows.ui.screens.tvshowListScreen.TvShowListScreen

@Composable
fun AppNavigationHost(navController: NavHostController = rememberNavController()) {

    val selectedTvShowID = "selected_tv_show_id"
    val selectedTvShowTitle = "title"

    NavHost(
        navController,
        startDestination = NavigationDestination.TvShowListScreenDestination.destination
    ) {
        composable(route = NavigationDestination.TvShowListScreenDestination.destination) {
            BaseScreen(
                title = stringResource(id = R.string.tvshow),
                isSecondaryHeader = false,
                onBackClick = {}
            ) {
                TvShowListScreen { id, name ->
                    navController.navigate(
                        "${NavigationDestination.TvShowDetailScreenDestination.destination}/$id/$name"
                    )
                }
            }
        }

        composable(
            route = "${NavigationDestination.TvShowDetailScreenDestination.destination}/{$selectedTvShowID}/{$selectedTvShowTitle}",
            arguments = listOf(
                navArgument(selectedTvShowID) {
                    type = NavType.IntType
                },
                navArgument(selectedTvShowTitle) {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString(selectedTvShowTitle)?.let { it1 ->
                BaseScreen(
                    title = it1,
                    isSecondaryHeader = true,
                    onBackClick = {
                        navController.navigateUp()
                    }
                ) {
                    val id = it.arguments!!.getInt(selectedTvShowID)
                    TvShowDetailsScreen(
                        selectedTvSeriesID = id
                    )
                }
            }
        }
    }
}

