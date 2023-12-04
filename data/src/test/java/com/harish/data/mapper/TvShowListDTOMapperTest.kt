package com.harish.data.mapper

import com.harish.data.TestData
import com.harish.data.TestData.tvShowDTO
import com.harish.data.TestData.tvShowListModel
import com.harish.data.TestData.tvShowModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TvShowListDTOMapperTest {
    private val tvShowDToMapper = mockk<TvShowDToMapper>()
    private lateinit var tvShowListDTOMapper: TvShowListDTOMapper

    @Before
    fun setup() {
        tvShowListDTOMapper = TvShowListDTOMapper(tvShowDToMapper = tvShowDToMapper)
    }

    @Test
    fun `tvShowListDTOMapper maps input TvShowListDTO to TvShowListModel`() {
        coEvery { tvShowDToMapper.mapFromDTOToDomain(tvShowDTO) } returns tvShowModel

        val result=tvShowListDTOMapper.mapFromDTOToDomain(TestData.tvShowListDTO)

        assertEquals(result.tvShowModelList[0].name,tvShowListModel.tvShowModelList[0].name)

    }
}