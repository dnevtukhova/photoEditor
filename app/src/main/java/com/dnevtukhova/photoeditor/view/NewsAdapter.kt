package com.dnevtukhova.photoeditor.view

import android.annotation.SuppressLint
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dnevtukhova.photoeditor.R
import com.dnevtukhova.photoeditor.api.NewsItem
import com.dnevtukhova.photoeditor.entity.FilterItem.FILTER_SEPIA
import com.dnevtukhova.photoeditor.entity.FilterItem.FILTER_WHITE_BLACK
import com.dnevtukhova.photoeditor.entity.FilterItem.NO_FILTER
import kotlinx.android.synthetic.main.news_item.view.*


class NewsAdapter constructor(private val inflater: LayoutInflater) :
    RecyclerView.Adapter<NewsViewHolder>() {
    private val items = mutableListOf<NewsItem>()
    fun setItems(news: List<NewsItem>) {
        items.clear()
        items.addAll(news)
        notifyDataSetChanged()
    }

    fun setFilter(filter: Int) {
        for (i in items) {
            i.filterItem = filter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(inflater.inflate(R.layout.news_item, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val newsTitle: TextView = view.newsTitle
    private val newsImage: ImageView = view.newsImage

    @SuppressLint("ResourceAsColor")
    fun bind(newsItem: NewsItem) {
        newsTitle.text = newsItem.title
        val cm = ColorMatrix()
        Glide.with(itemView.context)
            .load(newsItem.description)

            .placeholder(R.drawable.image_place_holder)
            .error(R.drawable.no_image)
            .centerCrop()
            .into(newsImage)
        when (newsItem.filterItem) {

            FILTER_WHITE_BLACK -> {

                cm.setSaturation(0f)
                newsImage.colorFilter = ColorMatrixColorFilter(cm)
                //  }
            }
            NO_FILTER -> {
                cm.setSaturation(1f)
                newsImage.colorFilter = ColorMatrixColorFilter(cm)
            }
            FILTER_SEPIA -> {
                // making image B&W
                cm.setSaturation(0f)
                val matrixB = ColorMatrix()
                // applying scales for RGB color values
                matrixB.setScale(1f, .95f, .82f, 1.0f)
                cm.setConcat(matrixB, cm)
                newsImage.colorFilter = ColorMatrixColorFilter(cm)
            }
        }
    }
}
