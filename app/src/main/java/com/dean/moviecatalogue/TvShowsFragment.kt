package com.dean.moviecatalogue

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.moviecatalogue.data.source.remote.api.ApiHelper.TYPE_TVSHOW
import kotlinx.android.synthetic.main.fragment_tv_shows.*

class TvShowsFragment : Fragment(), ListAdapter.DataCallback {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
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

        viewModel.getListOnTheAirTvShows().observe(viewLifecycleOwner, Observer { listTvShow ->
            rv_tv_shows.adapter.let { adapter ->
                when (adapter) {
                    is ListAdapter -> adapter.setData(listTvShow)
                }
            }
        })

    }

    private fun setupRecyclerView() {
        rv_tv_shows.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListAdapter(this@TvShowsFragment)
        }
    }

    override fun onItemClicked(data: ModelData) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.id)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        )
    }

}