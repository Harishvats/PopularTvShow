package com.harish.domain.usecases

import com.harish.domain.TestData.tvShowDetailsModel
import com.harish.domain.repository.PopularTvShowRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTvShowDetailUseCaseImplTest {
    private val popularTvShowRepository = mockk<PopularTvShowRepository>()
    private lateinit var getTvShowDetailUseCase: GetTvShowDetailsUseCase

    @Before
    fun setUp() {
        getTvShowDetailUseCase = GetTvShowDetailUseCaseImpl(popularTvShowRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GetTvShowDetailsUseCase returns Result of TvShowDetailsModel`()= runTest{

        coEvery { popularTvShowRepository.getTvShowDetails(100) } returns Result.success(tvShowDetailsModel)

        getTvShowDetailUseCase(100).onSuccess {
            assertEquals(it.id, tvShowDetailsModel.id)
        }

    }
}