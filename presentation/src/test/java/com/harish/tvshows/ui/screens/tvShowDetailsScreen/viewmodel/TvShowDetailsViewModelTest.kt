package com.harish.tvshows.ui.screens.tvShowDetailsScreen.viewmodel

import com.harish.domain.model.TvShowDetailsModel
import com.harish.domain.usecases.GetTvShowDetailsUseCase
import com.harish.tvshows.Dispatcher
import com.harish.tvshows.TestData
import com.harish.tvshows.mapper.toUiModel
import com.harish.tvshows.ui.screens.tvShowDetailsScreen.contract.TvShowDetailScreenContract
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TvShowDetailsViewModelTest {
    private lateinit var viewModel: TvShowDetailsViewModel
    private val useCase = mockk<GetTvShowDetailsUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var dispatcher = Dispatcher()

    @Before
    fun setup() {
        viewModel = TvShowDetailsViewModel(useCase)
    }

    @Test
    fun `fetchTvShowDetails should update state correctly on success`() = runTest {
        val seriesId = 100
        val response = Result.success(TestData.tvShowDetailsModel)


        coEvery { useCase(seriesId) } returns response
        viewModel.sendEvent(TvShowDetailScreenContract.ViewIntent.FetchTvShowDetails(seriesId))

        response.onSuccess {
            Assert.assertEquals(
                it.toUiModel().backdropPath,
                (viewModel.viewState.value as TvShowDetailScreenContract.ViewState.Success).data.backdropPath
            )
        }

    }

    @Test
    fun `fetchTvShowDetails should update state correctly on error`() = runTest {
        val seriesId = 100
        val exception = Exception("something went wrong")
        val response = Result.failure<TvShowDetailsModel>(exception)

        coEvery { useCase(seriesId) } returns response

        viewModel.sendEvent(TvShowDetailScreenContract.ViewIntent.FetchTvShowDetails(seriesId))


        Assert.assertEquals(
            exception.message,
            (viewModel.viewState.value as TvShowDetailScreenContract.ViewState.Error).message
        )

    }

}