package com.dean.moviecatalogue.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.moviecatalogue.*
import com.dean.moviecatalogue.adapter.ListAdapter
import com.dean.moviecatalogue.source.api.ApiHelper.TYPE_MOVIE
import com.dean.moviecatalogue.model.ModelData
import com.dean.moviecatalogue.viewmodel.MainViewModel
import com.dean.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(), ListAdapter.DataCallback {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        val factory = ViewModelFactory.getInstance()
        activity?.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[MainViewModel::class.java]
        }

        viewModel.getListNowPlayingMovies().observe(viewLifecycleOwner, Observer { listMovie ->
            rv_movies.adapter?.let { adapter ->
                when (adapter) {
                    is ListAdapter -> adapter.setData(listMovie)
                }
            }
        })

    }

    private fun setupRecyclerView() {
        rv_movies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListAdapter(this@MoviesFragment)
        }
    }

    override fun onItemClicked(data: ModelData) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.id)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }

}