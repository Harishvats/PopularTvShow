package com.harish.data.mapper

import com.harish.data.TestData.tvShowDTO
import com.harish.data.TestData.tvShowModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowDToMapperTest {
    private lateinit var tvShowDToMapper: TvShowDToMapper

    @Before
    fun setup() {
        tvShowDToMapper = TvShowDToMapper()
    }

    @Test
    fun `TvShowDToMapper maps input TvShowDTO to TvShowModel`() {

        val result=tvShowDToMapper.mapFromDTOToDomain(tvShowDTO)

        Assert.assertEquals(
            result.name,
            tvShowModel.name
        )

    }

}