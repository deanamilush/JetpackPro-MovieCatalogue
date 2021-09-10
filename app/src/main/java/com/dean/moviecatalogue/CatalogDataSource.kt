package com.dean.moviecatalogue

import androidx.lifecycle.LiveData

interface CatalogDataSource {

    fun getNowPlayingMovies(): LiveData<List<ModelData>>

    fun getMovieDetail(movieId: Int): LiveData<ModelData>

    fun getTvShowOnTheAir(): LiveData<List<ModelData>>

    fun getTvShowDetail(tvShowId: Int): LiveData<ModelData>

}