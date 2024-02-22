package com.zhalz.newz.ui.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.zhalz.newz.R
import com.zhalz.newz.adapter.NewsAdapter
import com.zhalz.newz.base.BaseActivity
import com.zhalz.newz.data.NewsData
import com.zhalz.newz.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getNews()

    }

    private fun getNews() {
        lifecycleScope.launch {
            viewModel.listUser.observe(this@MainActivity) {
                setRecycler(it)
            }
        }
    }

    private fun setRecycler(newsList: List<NewsData?>?) {
        val adapter = NewsAdapter()
        adapter.submitList(newsList)
        binding.rvNewsHome.adapter = adapter
    }
}