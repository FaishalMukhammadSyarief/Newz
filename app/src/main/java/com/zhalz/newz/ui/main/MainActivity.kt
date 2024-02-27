package com.zhalz.newz.ui.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.crocodic.core.base.adapter.ReactiveListAdapter
import com.crocodic.core.data.CoreSession
import com.zhalz.newz.R
import com.zhalz.newz.base.BaseActivity
import com.zhalz.newz.data.NewsData
import com.zhalz.newz.data.NewsResponse
import com.zhalz.newz.databinding.ActivityMainBinding
import com.zhalz.newz.databinding.ItemNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    @Inject
    lateinit var session: CoreSession

    private var query: String? = null
    private var filter: String? = null

    private val filterBottomSheet by lazy {
        BottomSheet(session) {
            val currentFilter= ArrayList<String>()

            if (session.getBoolean(BottomSheet.INDONESIA)) {
                currentFilter.add(BottomSheet.INDONESIA)
            }
            if (session.getBoolean(BottomSheet.ENGLISH)) {
                currentFilter.add(BottomSheet.ENGLISH)
            }
            if (session.getBoolean(BottomSheet.ARABIC)) {
                currentFilter.add(BottomSheet.ARABIC)
            }
            if (session.getBoolean(BottomSheet.CHINESE)) {
                currentFilter.add(BottomSheet.CHINESE)
            }
            if (session.getBoolean(BottomSheet.RUSSIAN)) {
                currentFilter.add(BottomSheet.RUSSIAN)
            }

            filter = currentFilter.joinToString(",")

            viewModel.getNews(query, filter)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getNews()

        setNews(viewModel.listNewsHome, binding.rvNewsHome)
        setNews(viewModel.listNewsSearch, binding.rvNewsSearch)

        binding.btnFilter.setOnClickListener {
            filterBottomSheet.show(supportFragmentManager, BottomSheet.TAG)
        }

    }

    private fun getNews() {
        viewModel.getNews()
        binding.searchView
            .editText
            .setOnEditorActionListener { _, _, _ ->
                query = binding.searchView.text.toString().trim()
                viewModel.getNews(query)
                true
            }
    }

    private fun setNews(listNews: SharedFlow<NewsResponse>, recyclerView: RecyclerView) {
        val adapter = ReactiveListAdapter<ItemNewsBinding, NewsData>(R.layout.item_news).initItem { _, _ -> }
        lifecycleScope.launch {
            listNews.collect { adapter.submitList(it.data) }
        }
        recyclerView.adapter = adapter
    }

}