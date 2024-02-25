package com.zhalz.newz.ui.main

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiObserver
import com.zhalz.newz.base.BaseViewModel
import com.zhalz.newz.data.NewsResponse
import com.zhalz.newz.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _listNewsHome = MutableSharedFlow<NewsResponse>()
    val listNewsHome = _listNewsHome.asSharedFlow()

    private val _listNewsSearch = MutableSharedFlow<NewsResponse>()
    val listNewsSearch = _listNewsSearch.asSharedFlow()

    fun getNews() {
        viewModelScope.launch {
            ApiObserver.run(
                { apiService.getNews(API_KEY) },
                false,
                object : ApiObserver.ResponseListenerFlow<NewsResponse>(_listNewsHome) {})
        }
    }

    fun getQueryNews(query: String) {
        viewModelScope.launch {
            ApiObserver.run(
                { apiService.getQueryNews(API_KEY, query) },
                false,
                object : ApiObserver.ResponseListenerFlow<NewsResponse>(_listNewsSearch) {})
        }
    }

    companion object {
        const val API_KEY = "pub_38692040c72123915418765ff9c7ea3cd795f"
    }
}
