package com.zhalz.newz.retrofit

import com.zhalz.newz.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("news/{apikey}")
    fun getNews( @Query (value = "apikey") apikey: String ) : Call<NewsResponse>
}