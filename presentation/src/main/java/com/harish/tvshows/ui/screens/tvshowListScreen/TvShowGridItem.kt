package com.harish.tvshows.ui.screens.tvshowListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.harish.tvshows.model.TvShowUiModel
import com.harish.tvshows.ui.components.CustomImage
import com.harish.tvshows.ui.components.CustomText

@Composable
fun TvShowGridItem(tvShow: TvShowUiModel, selectedTvShow: (Int, String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { selectedTvShow(tvShow.id, tvShow.name) }
        ) {
            CustomImage(
                data = tvShow.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomText(
                tvShow.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = Color.Black,
                maxLines = 1
            )
        }
    }
}
