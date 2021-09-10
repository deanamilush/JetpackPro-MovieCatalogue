package com.dean.moviecatalogue

import android.graphics.Color
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dean.moviecatalogue.data.source.remote.api.ApiHelper.API_IMAGE_ENDPOINT
import com.dean.moviecatalogue.data.source.remote.api.ApiHelper.ENDPOINT_POSTER_SIZE_W185
import com.dean.moviecatalogue.data.source.remote.api.ApiHelper.ENDPOINT_POSTER_SIZE_W780
import com.dean.moviecatalogue.data.source.remote.api.ApiHelper.TYPE_MOVIE
import com.dean.moviecatalogue.data.source.remote.api.ApiHelper.TYPE_TVSHOW
import com.dean.moviecatalogue.data.source.remote.api.ApiHelper.setImageWithGlide
import com.dean.moviecatalogue.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.items.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this@DetailActivity,
            factory
        )[DetailViewModel::class.java]

     //   setupToolbar()

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            setupToolbarTitle("Detail Movies")

            viewModel.getMovieDetail(id).observe(this, Observer {
                displayData(it)
            })


        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            setupToolbarTitle("Detail Tv Shows")

            viewModel.getTvShowDetail(id).observe(this, Observer {
                it?.let {
                    displayData(it)
                }
            })
        }


    }

    private fun displayData(data: ModelData) {
        tv_detail_name.text = data.name
        tv_detail_desc.text = data.desc
        setImageWithGlide(
            this@DetailActivity,
            API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + data.poster,
            img_detail_poster
        )

        setImageWithGlide(
            this@DetailActivity,
            API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W780 + data.img_preview,
            img_detail_hightlight
        )

    }

    /*private fun setupToolbar() {
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.setExpandedTitleColor(Color.TRANSPARENT)
    }*/

    private fun setupToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}