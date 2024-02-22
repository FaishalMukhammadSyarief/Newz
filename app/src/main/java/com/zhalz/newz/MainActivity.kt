package com.zhalz.newz

import android.os.Bundle
import com.crocodic.core.base.activity.NoViewModelActivity
import com.crocodic.core.base.adapter.ReactiveListAdapter
import com.zhalz.newz.data.NewsResponse
import com.zhalz.newz.databinding.ActivityMainBinding
import com.zhalz.newz.databinding.ItemNewsBinding

class MainActivity : NoViewModelActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val adapter by lazy {
        ReactiveListAdapter<ItemNewsBinding, NewsResponse>(R.layout.item_news).initItem { _ , _ ->

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvNewsHome.adapter = adapter

    }
}