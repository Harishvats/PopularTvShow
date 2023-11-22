package com.harish.data.mapper

import com.harish.data.model.TvShowDTO
import com.harish.data.model.TvShowListDTO
import com.harish.domain.model.TvShowListModel
import com.harish.domain.model.TvShowModel
import javax.inject.Inject

class TvShowListDTOMapper @Inject constructor(private val tvShowDToMapper: TvShowDToMapper) {

    fun mapFromDTOToDomain(tvShowListDTO: TvShowListDTO): TvShowListModel {
        return TvShowListModel(tvShowModelList = tvShowListDTO.tvShowDTOS.map {
            tvShowDToMapper.mapFromDTOToDomain(
                it
            )
        })
    }


}


class TvShowDToMapper @Inject constructor() {
    fun mapFromDTOToDomain(tvShowDTO: TvShowDTO): TvShowModel {
        return TvShowModel(
            backdropPath = tvShowDTO.backdropPath,
            id = tvShowDTO.id,
            name = tvShowDTO.name,
            popularity = tvShowDTO.popularity,
            posterPath = tvShowDTO.posterPath,
            voteAverage = tvShowDTO.voteAverage,
            voteCount = tvShowDTO.voteCount
        )
    }
}