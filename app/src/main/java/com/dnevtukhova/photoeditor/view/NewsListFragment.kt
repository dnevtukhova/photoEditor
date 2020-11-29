package com.dnevtukhova.photoeditor.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnevtukhova.photoeditor.R
import com.dnevtukhova.photoeditor.api.NetworkConstants.BASE_URL_HABR
import com.dnevtukhova.photoeditor.api.NetworkConstants.BASE_URL_LENTA
import com.dnevtukhova.photoeditor.api.NewsItem
import com.dnevtukhova.photoeditor.entity.FilterItem.FILTER_SEPIA
import com.dnevtukhova.photoeditor.entity.FilterItem.FILTER_WHITE_BLACK
import com.dnevtukhova.photoeditor.entity.FilterItem.NO_FILTER
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        buttonFilterBlackWhite.setOnClickListener {
            setFilter(FILTER_WHITE_BLACK)
        }
        buttonNoFilter.setOnClickListener {
            setFilter(NO_FILTER)
        }
        buttonFilterSepia.setOnClickListener {
            setFilter(FILTER_SEPIA)
        }
        swipeRefreshLayout.setOnRefreshListener {
            newsListPresenter.getNews(BASE_URL_LENTA)
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.name_habr -> {
              newsListPresenter.getNews(BASE_URL_HABR)
                return false
            }
            R.id.name_lenta -> {
                newsListPresenter.getNews(BASE_URL_LENTA)
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        newsListPresenter.clearDisposable()
    }

    override fun getNews(items: List<NewsItem>?) {
        if (items != null) {
            for (i in items) {
                Log.d(TAG, i.description)
            }
        }
        adapterNews.setItems(items!!)
    }

    override fun getError(error: String) {
        Toast.makeText(context, getString(R.string.error_text), Toast.LENGTH_LONG).show()
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

    override fun setFilter(filter: Int) {
        adapterNews.setFilter(filter)
        adapterNews.notifyDataSetChanged()
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