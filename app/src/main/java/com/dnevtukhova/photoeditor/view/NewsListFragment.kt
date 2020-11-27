package com.dnevtukhova.photoeditor.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnevtukhova.photoeditor.R
import com.dnevtukhova.photoeditor.api.NewsItem
import com.dnevtukhova.photoeditor.presenter.NewsPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_list_fragment.*
import kotlinx.android.synthetic.main.news_list_fragment.view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment:  MvpAppCompatFragment(), NewsListView {
    companion object {
        const val TAG = "NewsListFragment"
    }

    @Inject
    lateinit var presenterProvider: javax.inject.Provider<NewsPresenter>

    private lateinit var recycler: RecyclerView
    private lateinit var adapterNews: NewsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val newsListPresenter by moxyPresenter { presenterProvider.get() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        swipeRefreshLayout.setOnRefreshListener {
            newsListPresenter.getNews()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun getNews(items: List<NewsItem>?) {
        adapterNews.setItems(items!!)
    }

    override fun getError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        Log.d(TAG, error)
    }

    override fun showProgress() {
        progressbar.visibility = View.VISIBLE
        newsList.visibility = View.GONE
    }

    override fun hideProgress() {
        progressbar.visibility = View.GONE
        newsList.visibility = View.VISIBLE
    }

    private fun initRecycler(view: View) {
        recycler = view.newsList
        layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        adapterNews = NewsAdapter(
            LayoutInflater.from(context)
            )
        recycler.adapter = adapterNews
        adapterNews.notifyDataSetChanged()
    }
}