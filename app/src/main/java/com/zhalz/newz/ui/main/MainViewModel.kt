package com.zhalz.newz.ui.main

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiObserver
import com.zhalz.newz.base.BaseViewModel
import com.zhalz.newz.data.NewsResponse
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

    fun getNews(query: String? = null, language: String? = null) {
        viewModelScope.launch {
            ApiObserver.run(
                { apiService.getNews(API_KEY, query, language) },
                false,
                object : ApiObserver.ResponseListenerFlow<NewsResponse>(
                    if (query == null) _listNewsHome
                    else _listNewsSearch
                ) {})
        }
    }

    companion object {
        const val API_KEY = "pub_38692040c72123915418765ff9c7ea3cd795f"
    }
}
