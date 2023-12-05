package com.harish.tvshows.ui.screens.tvShowDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.harish.news.R
import com.harish.tvshows.model.TvShowDetailsUiModel
import com.harish.tvshows.ui.components.CustomImage
import com.harish.tvshows.ui.components.CustomText

@Composable
fun TvShowDetailCard(tvShowDetailsModel: TvShowDetailsUiModel) {

    Column(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CustomImage(
            data = tvShowDetailsModel.backdropPath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomText(
            tvShowDetailsModel.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            color = Color.Black, maxLines = 1
        )
        CustomText(
            tvShowDetailsModel.tagline,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            color = Color.Gray
        )

        CustomText(
            stringResource(id = R.string.overview),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
            color = Color.Black,
        )


        CustomText(
            tvShowDetailsModel.overview,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            color = Color.Black,
        )


    }
}