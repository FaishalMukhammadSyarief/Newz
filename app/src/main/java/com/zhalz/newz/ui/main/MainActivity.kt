package com.zhalz.newz.ui.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.zhalz.newz.R
import com.zhalz.newz.adapter.NewsAdapter
import com.zhalz.newz.base.BaseActivity
import com.zhalz.newz.data.NewsData
import com.zhalz.newz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getNewsHome()
        getNewsSearch()

    }

    private fun getNewsHome() {
        lifecycleScope.launch {
            viewModel.listNewsHome.observe(this@MainActivity) {
                setRvHome(it)
            }
        }
    }

    private fun getNewsSearch() {
        lifecycleScope.launch {

            binding.searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    val query = binding.searchView.text.toString()
                    viewModel.getQueryNews(query)
                    true
                }

            viewModel.listNewsSearch.observe(this@MainActivity) {
                setRvSearch(it)
            }
        }
    }

    private fun setRvHome(newsList: List<NewsData?>?) {
        val adapter = NewsAdapter()
        adapter.submitList(newsList)
        binding.rvNewsHome.adapter = adapter
    }

    private fun setRvSearch(newsList: List<NewsData?>?) {
        val adapter = NewsAdapter()
        adapter.submitList(newsList)
        binding.rvNewsSearch.adapter = adapter
    }
}