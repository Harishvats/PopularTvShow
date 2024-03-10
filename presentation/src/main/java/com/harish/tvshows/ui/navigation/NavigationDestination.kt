package com.harish.tvshows.ui.navigation

sealed class NavigationDestination(val destination: String) {
    object TvShowListScreenDestination : NavigationDestination(
        TV_SHOW_LIST_SCREEN_DESTINATION
    )
    object TvShowDetailScreenDestination : NavigationDestination(
        TV_SHOW_DETAIL_SCREEN_DESTINATION
    )
    companion object {
        const val TV_SHOW_LIST_SCREEN_DESTINATION = "tv_show_list_screen"
        const val TV_SHOW_DETAIL_SCREEN_DESTINATION = "tv_show_detail_screen"
    }

}
