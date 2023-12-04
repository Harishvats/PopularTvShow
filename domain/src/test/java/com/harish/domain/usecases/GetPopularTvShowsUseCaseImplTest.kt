package com.harish.domain.usecases

import com.harish.domain.TestData.tvShowListModel
import com.harish.domain.repository.PopularTvShowRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPopularTvShowsUseCaseImplTest{
    private val popularTvShowRepository= mockk<PopularTvShowRepository>()
    private lateinit var getPopularTvShowsUseCase:GetPopularTvShowsUseCase

    @Before
    fun setup(){
        getPopularTvShowsUseCase=GetPopularTvShowsUseCaseImpl(popularTvShowRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getPopularTvShowsUseCase returns Result of TvShowListModel`()= runTest{
        coEvery { popularTvShowRepository.getPopularTvShows() } returns Result.success(tvShowListModel)
        getPopularTvShowsUseCase().onSuccess {
            assertEquals(it.tvShowModelList[0].name, tvShowListModel.tvShowModelList[0].name)

        }
    }
}