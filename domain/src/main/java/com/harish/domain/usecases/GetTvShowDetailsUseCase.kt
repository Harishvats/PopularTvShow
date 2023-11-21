package com.harish.domain.usecases

import com.harish.core.common.Response
import com.harish.domain.model.TvShowDetailsModel

interface GetTvShowDetailsUseCase {
    suspend operator fun invoke(seriesId:Int):Response<TvShowDetailsModel>

}