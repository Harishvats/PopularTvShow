package com.harish.tvshows.ui.screens.tvshowListScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harish.domain.model.TvShowModel

@Composable
fun TvShowGrid(
    tvShowList: List<TvShowModel>,
    selectedTvShow: (Int, String) -> Unit,
    modifier: Modifier= Modifier
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        content = {
            items(tvShowList.size) {
                TvShowGridItem(tvShow = tvShowList[it], selectedTvShow)
            }
        })
}