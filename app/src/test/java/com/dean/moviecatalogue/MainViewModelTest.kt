package com.dean.moviecatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private val dummyMovie = DataDummy.generateDataMovieDummy()
    private val dummyTvShow = DataDummy.generateDataTvShowDummy()

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<ModelData>>

    @Before
    fun setUp() {
        viewModel = MainViewModel(catalogRepository)
    }

    @Test
    fun getListNowPlayingMovies() {
        val movie = MutableLiveData<List<ModelData>>()
        movie.value = dummyMovie

        Mockito.`when`(catalogRepository.getNowPlayingMovies()).thenReturn(movie)

        val dataListMovie = viewModel.getListNowPlayingMovies().value

        verify(catalogRepository).getNowPlayingMovies()
        Assert.assertNotNull(dataListMovie)
        Assert.assertEquals(10, dataListMovie?.size)

        viewModel.getListNowPlayingMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getListOnTheAirTvShows() {
        val tvShow = MutableLiveData<List<ModelData>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(catalogRepository.getTvShowOnTheAir()).thenReturn(tvShow)

        val dataListTvShow = viewModel.getListOnTheAirTvShows().value

        verify(catalogRepository).getTvShowOnTheAir()
        Assert.assertNotNull(dataListTvShow)
        Assert.assertEquals(10, dataListTvShow?.size)

        viewModel.getListOnTheAirTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}