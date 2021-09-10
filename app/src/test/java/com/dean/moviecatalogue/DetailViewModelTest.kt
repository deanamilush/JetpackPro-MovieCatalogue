package com.dean.moviecatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private val dummyMovie = DataDummy.generateDataMovieDummy()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShow = DataDummy.generateDataTvShowDummy()[0]
    private val tvShowId = dummyTvShow.id

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<ModelData>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<ModelData>()
        movieDummy.value = dummyMovie

        Mockito.`when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value as ModelData

        Assert.assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData.id)
        assertEquals(dummyMovie.name, movieData.name)
        assertEquals(dummyMovie.desc, movieData.desc)
        assertEquals(dummyMovie.poster, movieData.poster)
        assertEquals(dummyMovie.img_preview, movieData.img_preview)

        viewModel.getMovieDetail(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)

    }

    @Test
    fun getTvShowDetail() {
        val tvShowDummy = MutableLiveData<ModelData>()
        tvShowDummy.value = dummyTvShow

        Mockito.`when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getTvShowDetail(tvShowId).value as ModelData

        Assert.assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData.id)
        assertEquals(dummyTvShow.name, tvShowData.name)
        assertEquals(dummyTvShow.desc, tvShowData.desc)
        assertEquals(dummyTvShow.poster, tvShowData.poster)
        assertEquals(dummyTvShow.img_preview, tvShowData.img_preview)

        viewModel.getTvShowDetail(tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}