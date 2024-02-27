package com.zhalz.newz.retrofit

import com.zhalz.newz.data.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("news")
    suspend fun getNews(
        @Query ("apikey") apikey: String,
        @Query ("q") query: String?,
        @Query ("language") language: String?
    ) : NewsResponse

}