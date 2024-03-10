package com.harish.data.mapper

import com.harish.data.TestData.tvShowDetailsDTO
import com.harish.data.TestData.tvShowDetailsModel
import org.junit.Assert.assertEquals
import org.junit.Test


class TvShowDetailsMapperTest {
    @Test
    fun `toDomainModel should convert TvShowDetailsDTO to TvShowDetailsModel correctly`() {
        val result = tvShowDetailsDTO.toDomainModel()
        assertEquals(result.name, tvShowDetailsModel.name)
    }

}