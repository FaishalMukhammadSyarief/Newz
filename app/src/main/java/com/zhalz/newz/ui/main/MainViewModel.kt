package com.zhalz.newz.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhalz.newz.base.BaseViewModel
import com.zhalz.newz.data.NewsData
import com.zhalz.newz.data.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _listNewsHome = MutableLiveData<List<NewsData?>?>()
    val listNewsHome = _listNewsHome

    private val _listNewsSearch = MutableLiveData<List<NewsData?>?>()
    val listNewsSearch = _listNewsSearch

    init {
        getNews()
    }

        private fun getNews() = viewModelScope.launch {

            apiService.getNews(API_KEY)
                .enqueue(object : Callback<NewsResponse> {

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

/*    private fun getNews() {
        viewModelScope.launch {
            ApiObserver.run(
                { apiService.getNews(API_KEY) },
                false, object : ApiObserver.ResponseListenerFlow<NewsResponse>(_listNewsHome) {

                })
        }
    }*/

    fun getQueryNews(query: String) = viewModelScope.launch {

        apiService.getQueryNews(API_KEY, query)
            .enqueue(object : Callback<NewsResponse> {

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

    companion object {
        const val API_KEY = "pub_38692040c72123915418765ff9c7ea3cd795f"
    }

}