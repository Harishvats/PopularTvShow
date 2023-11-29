package com.harish.domain.usecases

import com.harish.domain.model.TvShowListModel

interface GetPopularTvShowsUseCase{
suspend operator fun invoke():Result<TvShowListModel>
}