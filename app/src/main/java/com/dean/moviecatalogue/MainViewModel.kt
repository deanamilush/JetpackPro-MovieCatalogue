package com.dean.moviecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getListNowPlayingMovies(): LiveData<List<ModelData>> = catalogRepository.getNowPlayingMovies()

    fun getListOnTheAirTvShows(): LiveData<List<ModelData>> = catalogRepository.getTvShowOnTheAir()

}