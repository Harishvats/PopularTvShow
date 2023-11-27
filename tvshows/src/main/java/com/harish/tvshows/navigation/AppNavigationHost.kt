package com.harish.tvshows.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.harish.tvshows.AppConstants.Companion.SELECTED_TV_SHOW_ID
import com.harish.tvshows.AppConstants.Companion.SELECTED_TV_SHOW_TITLE
import com.harish.tvshows.AppConstants.Companion.TV_SHOW_DETAIL_SCREEN_DESTINATION
import com.harish.tvshows.AppConstants.Companion.TV_SHOW_LIST_SCREEN_DESTINATION
import com.harish.tvshows.ui.screens.tvshowListScreen.TvShowListScreen
import com.harish.tvshows.ui.screens.tvshowListScreen.TvShowListViewModel

@Composable
fun AppNavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController, startDestination = TV_SHOW_LIST_SCREEN_DESTINATION
    ) {
        composable(route = TV_SHOW_LIST_SCREEN_DESTINATION) {
            val tvShowListViewModel = hiltViewModel<TvShowListViewModel>()
            TvShowListScreen(tvShowListViewModel = tvShowListViewModel) { id, Name ->

            }
        }

        composable(
            route = "$TV_SHOW_DETAIL_SCREEN_DESTINATION/$SELECTED_TV_SHOW_ID/$SELECTED_TV_SHOW_TITLE",
            arguments = listOf(navArgument(SELECTED_TV_SHOW_ID) {
                type = NavType.IntType
            }, navArgument(SELECTED_TV_SHOW_TITLE) {
                type = NavType.StringType
            })
        ) {

        }
    }
}