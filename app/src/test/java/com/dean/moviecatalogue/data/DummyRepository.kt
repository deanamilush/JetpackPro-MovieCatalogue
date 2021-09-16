package com.dean.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dean.moviecatalogue.source.remote.CatalogDataSource
import com.dean.moviecatalogue.model.ModelData
import com.dean.moviecatalogue.source.remote.RemoteDataSource
import com.dean.moviecatalogue.source.api.MovieResponse
import com.dean.moviecatalogue.source.api.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DummyRepository(private val remoteDataSource: RemoteDataSource) : CatalogDataSource {

    override fun getNowPlayingMovies(): LiveData<List<ModelData>> {
        val listMovieResult = MutableLiveData<List<ModelData>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getNowPlayingMovies(object : RemoteDataSource.LoadNowPlayingMoviesCallback{
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<ModelData>()
                    for (response in movieResponse){
                        val movie = ModelData(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview
                        )

                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<ModelData> {
        val movieResult = MutableLiveData<ModelData>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback{
                override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                    val movie = ModelData(
                        movieResponse.id,
                        movieResponse.name,
                        movieResponse.desc,
                        movieResponse.poster,
                        movieResponse.img_preview
                    )

                    movieResult.postValue(movie)
                }
            })
        }

        return movieResult
    }

    override fun getTvShowOnTheAir(): LiveData<List<ModelData>> {
        val listTvShowResult = MutableLiveData<List<ModelData>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowOnTheAir(object : RemoteDataSource.LoadOnTheAirTvShowCallback{
                override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                    val tvShowList = ArrayList<ModelData>()
                    for (response in tvShowResponse){
                        val tvShow = ModelData(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview
                        )
                        tvShowList.add(tvShow)
                    }

                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        return listTvShowResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<ModelData> {
        val tvShowResult = MutableLiveData<ModelData>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.LoadTvShowDetailCallback{
                override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
                    val tvShow = ModelData(
                        tvShowResponse.id,
                        tvShowResponse.name,
                        tvShowResponse.desc,
                        tvShowResponse.poster,
                        tvShowResponse.img_preview
                    )

                    tvShowResult.postValue(tvShow)
                }
            })
        }

        return tvShowResult
    }

}