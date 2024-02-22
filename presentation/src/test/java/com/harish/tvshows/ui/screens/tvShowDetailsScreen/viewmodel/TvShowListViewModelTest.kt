package com.harish.tvshows.ui.screens.tvShowDetailsScreen.viewmodel

import app.cash.turbine.test
import com.harish.domain.model.TvShowListModel
import com.harish.domain.usecases.GetPopularTvShowsUseCase
import com.harish.tvshows.Dispatcher
import com.harish.tvshows.TestData
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


        coEvery { useCase() } answers { response }
        viewModel.sendEvent(TvShowListScreenContract.ViewIntent.FetchTvShowList)

        viewModel.viewState.test {
            Assert.assertTrue(awaitItem() is TvShowListScreenContract.ViewState.Success)
        }

    }

    @Test
    fun `getTvShowList should update state correctly on error`() = runTest {
        val exception = Exception("something went wrong")
        val response = Result.failure<TvShowListModel>(exception)

        coEvery { useCase() } answers { response }

        with(viewModel) {
            sendEvent(TvShowListScreenContract.ViewIntent.FetchTvShowList)
            viewState.test {
                Assert.assertTrue(awaitItem() is TvShowListScreenContract.ViewState.Error)
            }
        }
    }

    @Test
    fun `navigate to details screen when OnTvShowClicked intent passed`() =
        runTest {
            with(viewModel) {
                sideEffect.test {
                    sendEvent(TvShowListScreenContract.ViewIntent.OnTvShowClicked(TestData.tvShowDetailsModel.id,TestData.tvShowDetailsModel.name))
                    Assert.assertTrue(awaitItem() is TvShowListScreenContract.SideEffect.NavigateToDetailsScreen)
                }
            }
        }

}