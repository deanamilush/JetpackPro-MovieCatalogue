package com.dean.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dean.moviecatalogue.R
import com.dean.moviecatalogue.source.api.ApiHelper.API_IMAGE_ENDPOINT
import com.dean.moviecatalogue.source.api.ApiHelper.ENDPOINT_POSTER_SIZE_W185
import com.dean.moviecatalogue.source.api.ApiHelper.setImageWithGlide
import com.dean.moviecatalogue.model.ModelData
import kotlinx.android.synthetic.main.items.view.*
import java.util.ArrayList

class ListAdapter(private val callback: DataCallback) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private val listData = ArrayList<ModelData>()

    fun setData(data: List<ModelData>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ModelData) {
            with(itemView) {
                data.poster?.let {
                    setImageWithGlide(
                        context,
                        API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + it,
                        poster
                    )
                }
                tv_item_title.text = data.name
                tv_description.text = data.desc

                cv_item_course.setOnClickListener {
                    callback.onItemClicked(data)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    interface DataCallback  {
        fun onItemClicked(data: ModelData)
    }
}