package com.harish.data.repository.datasource

import com.harish.data.BuildConfig
import com.harish.data.TestData.listErrorResponse
import com.harish.data.TestData.tvShowDetailsDTO
import com.harish.data.TestData.tvShowDetailsModel
import com.harish.data.TestData.tvShowListDTO
import com.harish.data.TestData.tvShowListModel
import com.harish.data.api.APIService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class RemoteDataSourceImplTest {

    private val apiService: APIService = mockk()

    private lateinit var remoteDataSourceImpl: RemoteDataSource

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        remoteDataSourceImpl =
            RemoteDataSourceImpl(apiService,testDispatcher)
    }

    @Test
    fun `getPopularTvShows() on success returns Result of TvShowListModel`() = runBlocking {
        coEvery { apiService.getPopularTvShows(BuildConfig.API_KEY) } returns tvShowListDTO
        val result = remoteDataSourceImpl.getPopularTvShows()

        assertEquals(result, Result.success(tvShowListModel))
    }

    @Test
    fun `getPopularTvShows() on HttpException in api call returns failure Result with exception message`() =
        runBlocking {
            coEvery { apiService.getPopularTvShows(BuildConfig.API_KEY) } throws HttpException(
                listErrorResponse
            )

            val result = remoteDataSourceImpl.getPopularTvShows()

            assert(result.isFailure)
        }

    @Test
    fun `getTvShowDetails() on success returns Result of TvShowDetailsModel`() = runBlocking {
        coEvery {
            apiService.getTvShowDetails(
                100,
                BuildConfig.API_KEY
            )
        } returns tvShowDetailsDTO


        val result = remoteDataSourceImpl.getTvShowDetails(100)

        assertEquals(result, Result.success(tvShowDetailsModel))
    }

    @Test
    fun `getTvShowDetails() on HttpException in api call returns failure Result with exception message`() =
        runBlocking {
            coEvery {
                apiService.getTvShowDetails(
                    100,
                    BuildConfig.API_KEY
                )
            } throws HttpException(
                listErrorResponse
            )

            val result = remoteDataSourceImpl.getTvShowDetails(100)

            assert(result.isFailure)
        }

}
