package com.harish.tvshows.mapper

import com.harish.tvshows.TestData.tvShowDetailsModel
import com.harish.tvshows.TestData.tvShowDetailsUiModel
import org.junit.Assert
import org.junit.Test


class TvShowDetailsUiMapperTest {

    @Test
    fun `toUiModel should convert TvShowDetailsModel to TvShowDetailsUiModel correctly`() {
        val result = tvShowDetailsModel.toUiModel()
        Assert.assertEquals(result.backdropPath, tvShowDetailsUiModel.backdropPath)
    }
}