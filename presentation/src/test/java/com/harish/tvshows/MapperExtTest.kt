package com.harish.tvshows

import com.harish.tvshows.TestData.tvShowDetailsModel
import com.harish.tvshows.TestData.tvShowDetailsUiModel
import com.harish.tvshows.TestData.tvShowListModel
import com.harish.tvshows.TestData.tvShowListUiModel
import com.harish.tvshows.TestData.tvShowModel
import com.harish.tvshows.TestData.tvShowUiModel
import com.harish.tvshows.mapper.toUiModel
import org.junit.Assert.assertEquals
import org.junit.Test


class MapperExtTest {

    @Test
    fun `toUiModel should convert TvShowModel to TvShowUiModel correctly`() {
        val result = tvShowModel.toUiModel()
        assertEquals(result.posterPath, tvShowUiModel.posterPath)

    }

    @Test
    fun `toUiModel should convert TvShowListModel to TvShowListUiModel correctly`() {
        val result = tvShowListModel.toUiModel()
        assertEquals(
            result.tvShowModelList[0].backdropPath,
            tvShowListUiModel.tvShowModelList[0].backdropPath
        )
    }

    @Test
    fun `toUiModel should convert TvShowDetailsModel to TvShowDetailsUiModel correctly`() {
        val result = tvShowDetailsModel.toUiModel()
        assertEquals(result.posterPath, tvShowDetailsUiModel.posterPath)
    }


}