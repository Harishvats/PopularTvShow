package com.harish.data.repository

import com.harish.data.TestData.tvShowDetailsModel
import com.harish.data.TestData.tvShowListModel
import com.harish.data.TestData.tvShowModel
import com.harish.data.repository.datasource.RemoteDataSource
import com.harish.domain.repository.PopularTvShowRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PopularTvShowRepositoryImplTest {

    private val remoteDataSource = mockk<RemoteDataSource>()

    private lateinit var popularTvShowRepository: PopularTvShowRepository

    @Before
    fun setup() {
        popularTvShowRepository = PopularTvShowRepositoryImpl(remoteDataSource)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getPopularTvShows() returns success Result of TvListModel`()= runTest{

        coEvery { remoteDataSource.getPopularTvShows() } returns Result.success(tvShowListModel)
        val result=popularTvShowRepository.getPopularTvShows()

        result.onSuccess {
            assertEquals(it.tvShowModelList[0].name, tvShowListModel.tvShowModelList[0].name)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getTvShowDetails() returns success Result of TvShowDetailsModel`()= runTest{

        coEvery { remoteDataSource.getTvShowDetails(100) } returns Result.success(tvShowDetailsModel)
        val result=popularTvShowRepository.getTvShowDetails(100)

        result.onSuccess {
            assertEquals(it.name, tvShowModel.name)
        }
    }

}