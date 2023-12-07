package com.harish.data.repository.datasource

import com.harish.data.BuildConfig
import com.harish.data.TestData.IOResponseErrorMessage
import com.harish.data.TestData.detailsErrorResponse
import com.harish.data.TestData.listErrorResponse
import com.harish.data.TestData.tvShowDetailsDTO
import com.harish.data.TestData.tvShowDetailsModel
import com.harish.data.TestData.tvShowListDTO
import com.harish.data.TestData.tvShowListModel
import com.harish.data.api.APIService
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class RemoteDataSourceImplTest {

    private val apiService: APIService = mockk()

    private lateinit var remoteDataSourceImpl: RemoteDataSource

    @Before
    fun setUp() {
        remoteDataSourceImpl =
            RemoteDataSourceImpl(apiService)
    }

    @Test
    fun `getPopularTvShows() on success returns Result of TvShowListModel`() = runBlocking {
        coEvery { apiService.getPopularTvShows(BuildConfig.API_KEY) } returns Response.success(
            tvShowListDTO
        )
//        coEvery { tvShowListDTOMapper.mapFromDTOToDomain(tvShowListDTO) } returns tvShowListModel

        val result = remoteDataSourceImpl.getPopularTvShows()

        assertEquals(result, Result.success(tvShowListModel))
    }

    @Test
    fun `getPopularTvShows() on failure returns failure message as a Throwable`() = runBlocking {
        coEvery { apiService.getPopularTvShows(BuildConfig.API_KEY) } returns listErrorResponse

        val result = remoteDataSourceImpl.getPopularTvShows()

        assert(result.isFailure)
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
    fun `getPopularTvShows() on IOException in api call returns failure Result with exception message`() =
        runBlocking {
            coEvery { apiService.getPopularTvShows(BuildConfig.API_KEY) } throws IOException(
                IOResponseErrorMessage
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
        } returns Response.success(
            tvShowDetailsDTO
        )
//        coEvery { tvShowDetailDtoMapper.mapFromDTOToDomain(tvShowDetailsDTO) } returns tvShowDetailsModel

        val result = remoteDataSourceImpl.getTvShowDetails(100)

        assertEquals(result, Result.success(tvShowDetailsModel))
    }

    @Test
    fun `getTvShowDetails() on failure returns failure message as a Throwable`() = runBlocking {
        coEvery {
            apiService.getTvShowDetails(100, BuildConfig.API_KEY)
        } returns detailsErrorResponse

        val result = remoteDataSourceImpl.getTvShowDetails(100)

        assert(result.isFailure)
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

    @Test
    fun `getTvShowDetails() on IOException in api call returns failure Result with exception message`() =
        runBlocking {
            coEvery {
                apiService.getTvShowDetails(
                    100,
                    BuildConfig.API_KEY
                )
            } throws IOException(
                IOResponseErrorMessage
            )

            val result = remoteDataSourceImpl.getTvShowDetails(100)

            assert(result.isFailure)
        }
}
