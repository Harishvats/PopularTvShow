package com.harish.tvshows.mapper

import com.harish.tvshows.TestData.tvShowListModel
import com.harish.tvshows.TestData.tvShowListUiModel
import com.harish.tvshows.TestData.tvShowModel
import com.harish.tvshows.TestData.tvShowUiModel
import org.junit.Assert
import org.junit.Test


class TvShowListUiMapperTest {

    @Test
    fun `toUiModel should convert TvShowModel to TvShowUiModel correctly`() {
        val result = tvShowModel.toUiModel()
        Assert.assertEquals(result.name, tvShowUiModel.name)

    }

    @Test
    fun `toUiModel should convert TvShowListModel to TvShowListUiModel correctly`() {
        val result = tvShowListModel.toUiModel()
        Assert.assertEquals(result.tvShowModelList[0].posterPath, tvShowListUiModel.tvShowModelList[0].posterPath)
    }

}