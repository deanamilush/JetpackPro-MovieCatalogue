package com.dean.moviecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<ModelData> =
        catalogRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<ModelData> = catalogRepository.getTvShowDetail(
        tvShowId
    )
}