package com.harish.tvshows.ui.screens.tvShowDetailsScreen.viewmodel

import com.harish.domain.model.TvShowListModel
import com.harish.domain.usecases.GetPopularTvShowsUseCase
import com.harish.tvshows.Dispatcher
import com.harish.tvshows.TestData
import com.harish.tvshows.mapper.toUiModel
import com.harish.tvshows.ui.screens.tvshowListScreen.contract.TvShowListScreenContract
import com.harish.tvshows.ui.screens.tvshowListScreen.viewmodel.TvShowListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TvShowListViewModelTest {
    private lateinit var viewModel: TvShowListViewModel
    private val useCase = mockk<GetPopularTvShowsUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var dispatcher = Dispatcher()

    @Before
    fun setup() {
        viewModel = TvShowListViewModel(useCase)
    }

    @Test
    fun `getTvShowList should update state correctly on success`() = runTest {
        val response = Result.success(TestData.tvShowListModel)


        coEvery { useCase() } returns response
        viewModel.sendEvent(TvShowListScreenContract.ViewIntent.FetchTvShowList)

        response.onSuccess {
            Assert.assertEquals(
                it.toUiModel().tvShowModelList.size,
                (viewModel.viewState.value as TvShowListScreenContract.ViewState.Success).data.tvShowModelList.size
            )
        }

    }

    @Test
    fun `getTvShowList should update state correctly on error`() = runTest {
        val exception = Exception("something went wrong")
        val response = Result.failure<TvShowListModel>(exception)

        coEvery { useCase() } returns response

        viewModel.sendEvent(TvShowListScreenContract.ViewIntent.FetchTvShowList)


        Assert.assertEquals(
            exception.message,
            (viewModel.viewState.value as TvShowListScreenContract.ViewState.Error).message
        )

    }

}