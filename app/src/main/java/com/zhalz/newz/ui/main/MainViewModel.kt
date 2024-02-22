package com.zhalz.newz.ui.main

import androidx.lifecycle.MutableLiveData
import com.zhalz.newz.base.BaseViewModel
import com.zhalz.newz.data.NewsData
import com.zhalz.newz.data.NewsResponse
import com.zhalz.newz.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainViewModel : BaseViewModel() {

    private val _listNewsHome = MutableLiveData<List<NewsData?>?>()
    val listNewsHome = _listNewsHome

        private val _listNewsSearch = MutableLiveData<List<NewsData?>?>()
        val listNewsSearch = _listNewsSearch

    init {
        getNews()
    }

    private fun getNews() {

        val client = ApiConfig.getApiService().getNews("pub_38692040c72123915418765ff9c7ea3cd795f")
        client.enqueue(object : Callback<NewsResponse> {

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _listNewsHome.value = response.body()?.results
                } else {
                    Timber.e("onFailure: " + response.message())
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Timber.tag("MainViewModel").e("onFailure: %s", t.message.toString())
            }

        })

    }

        fun getQueryNews(query: String) {

            val client = ApiConfig.getApiService().getQueryNews("pub_38692040c72123915418765ff9c7ea3cd795f", query)
            client.enqueue(object : Callback<NewsResponse> {

                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        _listNewsSearch.value = response.body()?.results
                    } else {
                        Timber.e("onFailure: " + response.message())
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Timber.tag("MainViewModel").e("onFailure: %s", t.message.toString())
                }

            })

        }

}