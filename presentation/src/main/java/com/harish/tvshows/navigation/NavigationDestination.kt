package com.harish.tvshows.navigation

import com.harish.tvshows.utils.AppConstants

sealed class NavigationDestination(val destination: String) {
    object TvShowListScreenDestination : NavigationDestination(
        AppConstants.TV_SHOW_LIST_SCREEN_DESTINATION
    )
    object TvShowDetailScreenDestination : NavigationDestination(
        AppConstants.TV_SHOW_DETAIL_SCREEN_DESTINATION
    )
}
