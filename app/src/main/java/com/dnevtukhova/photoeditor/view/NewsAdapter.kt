package com.dnevtukhova.photoeditor.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dnevtukhova.photoeditor.R
import com.dnevtukhova.photoeditor.api.NewsItem
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter constructor(private val inflater: LayoutInflater): RecyclerView.Adapter<NewsViewHolder>() {
    private val items = mutableListOf<NewsItem>()
     fun setItems(news: List<NewsItem>) {
         items.clear()
         items.addAll(news)
         notifyDataSetChanged()
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

class NewsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val newsTitle: TextView = view.newsTitle
    private val newsImage: ImageView = view.newsImage

    fun bind (newsItem: NewsItem) {
        newsTitle.text = newsItem.title
        Glide.with(itemView.context)
            .load(newsItem.description)
            .placeholder(R.color.design_default_color_background)
           // .error(R.drawable.no_image)
            .centerCrop()
            .into(newsImage)

    }

}
