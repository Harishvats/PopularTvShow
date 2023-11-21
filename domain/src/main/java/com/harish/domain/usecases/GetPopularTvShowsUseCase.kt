package com.harish.domain.usecases

import com.harish.core.common.Response
import com.harish.domain.model.TvShowListModel

interface GetPopularTvShowsUseCase{
suspend operator fun invoke():Response<TvShowListModel>
}