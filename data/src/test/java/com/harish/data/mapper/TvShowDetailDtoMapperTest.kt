package com.harish.data.mapper

import com.harish.data.TestData.tvShowDetailsDTO
import com.harish.data.TestData.tvShowDetailsModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TvShowDetailDtoMapperTest {

    private lateinit var tvShowDetailDtoMapper: TvShowDetailDtoMapper

    @Before
    fun setup() {
        tvShowDetailDtoMapper = TvShowDetailDtoMapper()
    }

    @Test
    fun `tvShowDetailDtoMapper maps input TvShowDetailsDTO to TvShowDetails`() {

        val result=tvShowDetailDtoMapper.mapFromDTOToDomain(tvShowDetailsDTO)
        assertEquals(result.id, tvShowDetailsModel.id)
    }
}