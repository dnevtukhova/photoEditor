package com.dnevtukhova.photoeditor.interactor

import com.dnevtukhova.photoeditor.api.News
import io.reactivex.Observable


interface NewsListInteractor {
    fun getNews(): Observable<News>
}