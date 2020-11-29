package com.dnevtukhova.photoeditor.interactor

import com.dnevtukhova.photoeditor.api.News
import com.dnevtukhova.photoeditor.api.ServerApi
import io.reactivex.Observable
import javax.inject.Inject

class NewsListInteractorImpl @Inject constructor(private val api: ServerApi) : NewsListInteractor {

    override fun getNews(url: String): Observable<News> {
        return api.getNews(url)
    }
}