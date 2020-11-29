package com.dnevtukhova.photoeditor.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ServerApi {
    @GET
    fun getNews(@Url url: String): Observable<News>
}