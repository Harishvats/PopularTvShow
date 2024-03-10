package com.harish.data.mapper

import com.harish.data.TestData.tvShowDTO
import com.harish.data.TestData.tvShowListDTO
import com.harish.data.TestData.tvShowListModel
import com.harish.data.TestData.tvShowModel
import org.junit.Assert.assertEquals
import org.junit.Test


class TvShowListMapperTest {

    @Test
    fun `toDomainModel should convert TvShowDTO to TvShowModel correctly`() {
        val result = tvShowDTO.toDomainModel()
        assertEquals(result.name, tvShowModel.name)

    }

    @Test
    fun `toDomainModel should convert TvShowListDTO to TvShowListModel correctly`() {
        val result = tvShowListDTO.toDomainModel()
        assertEquals(result.tvShowModelList[0].name, tvShowListModel.tvShowModelList[0].name)
    }

}