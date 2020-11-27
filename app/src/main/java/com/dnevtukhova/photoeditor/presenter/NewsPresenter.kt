package com.dnevtukhova.photoeditor.presenter

import android.util.Log
import android.widget.Toast
import com.dnevtukhova.photoeditor.api.NewsItem
import com.dnevtukhova.photoeditor.interactor.NewsListInteractor
import com.dnevtukhova.photoeditor.view.NewsListFragment
import com.dnevtukhova.photoeditor.view.NewsListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor(
    private val newsInteractor: NewsListInteractor
) : MvpPresenter<NewsListView>() {
    companion object {
        const val TAG = "NewsPresenter"
    }

    lateinit var disposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d(TAG, "мы в методе onFirstViewAttach")
        getNews()
    }

    fun getNews() {
        viewState.showProgress()
        Log.d(TAG, "в методе getNews")
        disposable = newsInteractor.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {

                    viewState.getError(it.toString())
                    viewState.hideProgress()
                },
                onNext = {

                    for(i in it.channel!!.newsList!!) {
                        i.description = getLink(i.description)
                    }
                    viewState.getNews(it.channel?.newsList)
                    viewState.hideProgress()
                },
                onComplete = { Log.d(TAG, "в колбэке onComplete") }
            )

    }

    //получить ссылку на картинку из поля Описание
    fun getLink (htmlStringDescription: String): String {
        var htmlString = htmlStringDescription
        if(htmlString.contains("src=\"")) {
            htmlString = htmlString.substring(htmlString.indexOf("src=\""))
            htmlString = htmlString.substring(("src=\"").length);
            htmlString = htmlString.substring(0, htmlString.indexOf("\""))}
        //   println("ссылка на картинку - $htmlString")
        return htmlString
    }
}