package com.harish.tvshows.mapper

import com.harish.tvshows.TestData.tvShowDetailsModel
import com.harish.tvshows.TestData.tvShowDetailsUiModel
import com.harish.tvshows.TestData.tvShowListModel
import com.harish.tvshows.TestData.tvShowListUiModel
import com.harish.tvshows.TestData.tvShowModel
import com.harish.tvshows.TestData.tvShowUiModel
import org.junit.Assert
import org.junit.Test


class MapperExtTest {

    @Test
    fun `toUiModel should convert TvShowModel to TvShowUiModel correctly`() {
        val result = tvShowModel.toUiModel()
        Assert.assertEquals(result.name, tvShowUiModel.name)

    }

    @Test
    fun `toUiModel should convert TvShowListModel to TvShowListUiModel correctly`() {
        val result = tvShowListModel.toUiModel()
        Assert.assertEquals(result.tvShowModelList[0].name, tvShowListUiModel.tvShowModelList[0].name)
    }

    @Test
    fun `toUiModel should convert TvShowDetailsModel to TvShowDetailsUiModel correctly`() {
        val result = tvShowDetailsModel.toUiModel()
        Assert.assertEquals(result.backdropPath, tvShowDetailsUiModel.backdropPath)
    }
}