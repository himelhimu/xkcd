package com.himel.apps.xkcd.network

import com.himel.apps.xkcd.models.Comic
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("info.0.json")
    suspend fun getCurrentComic():Comic

    @GET("{id}/info.0.json")
    suspend fun getComicWithNumber(@Path("id")id:Int):Comic
}