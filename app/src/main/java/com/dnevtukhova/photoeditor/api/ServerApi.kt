package com.dnevtukhova.photoeditor.api

import io.reactivex.Observable
import retrofit2.http.GET

interface ServerApi {
    @GET("all/")
    fun getNewsFromHabr(): Observable<News>
}