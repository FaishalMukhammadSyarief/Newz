package com.zhalz.newz.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.crocodic.core.base.adapter.ReactiveListAdapter
import com.zhalz.newz.R
import com.zhalz.newz.base.BaseActivity
import com.zhalz.newz.data.NewsData
import com.zhalz.newz.databinding.ActivityMainBinding
import com.zhalz.newz.databinding.ItemNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getNews()

        setNews(viewModel.listNewsHome, binding.rvNewsHome)
        setNews(viewModel.listNewsSearch, binding.rvNewsSearch)

    }

    private fun getNews() {
        lifecycleScope.launch {

            viewModel.getNews()

            binding.searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    val query = binding.searchView.text.toString()
                    viewModel.getQueryNews(query)
                    true
                }

        }
    }

    private fun setNews(listNews: LiveData<List<NewsData?>?>, recyclerView: RecyclerView) {
        lifecycleScope.launch {

            val adapter =
                ReactiveListAdapter<ItemNewsBinding, NewsData>(R.layout.item_news).initItem { _, _ -> }
            listNews.observe(this@MainActivity) { adapter.submitList(it) }
            recyclerView.adapter = adapter

        }
    }

}