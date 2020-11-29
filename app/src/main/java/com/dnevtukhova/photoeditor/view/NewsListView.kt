package com.dnevtukhova.photoeditor.view

import com.dnevtukhova.photoeditor.api.NewsItem
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface NewsListView: MvpView {
    @AddToEndSingle
    fun getNews(items: List<NewsItem>?)

    @OneExecution
    fun getError(error: String)

    @AddToEndSingle
    fun showProgress()

    @AddToEndSingle
    fun hideProgress()

    @AddToEndSingle
    fun setFilter(filter: Int)
}