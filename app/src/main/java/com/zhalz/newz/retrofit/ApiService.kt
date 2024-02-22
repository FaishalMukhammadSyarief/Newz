package com.zhalz.newz.retrofit

import com.zhalz.newz.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("news")
    fun getNews( @Query ("apikey") apikey: String ) : Call<NewsResponse>

    @GET("news")
    fun getQueryNews(
        @Query ("apikey") apikey: String,
        @Query ("q") query: String
    ) : Call<NewsResponse>
}