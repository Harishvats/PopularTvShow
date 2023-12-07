package com.harish.tvshows.navigation

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
import com.harish.tvshows.utils.AppConstants.Companion.SELECTED_TV_SHOW_ID
import com.harish.tvshows.utils.AppConstants.Companion.SELECTED_TV_SHOW_TITLE

@Composable
fun AppNavigationHost(navController: NavHostController = rememberNavController()) {
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
            route = "${NavigationDestination.TvShowDetailScreenDestination.destination}/{$SELECTED_TV_SHOW_ID}/{$SELECTED_TV_SHOW_TITLE}",
            arguments = listOf(
                navArgument(SELECTED_TV_SHOW_ID) {
                    type = NavType.IntType
                },
                navArgument(SELECTED_TV_SHOW_TITLE) {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString(SELECTED_TV_SHOW_TITLE)?.let { it1 ->
                BaseScreen(
                    title = it1,
                    isSecondaryHeader = true,
                    onBackClick = {
                        navController.navigateUp()
                    }
                ) {
                    val id = it.arguments!!.getInt(SELECTED_TV_SHOW_ID)
                    TvShowDetailsScreen(
                        selectedTvSeriesID = id
                    )
                }
            }
        }
    }
}
