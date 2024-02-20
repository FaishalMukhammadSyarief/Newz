package com.zhalz.newz

import android.os.Bundle
import com.crocodic.core.base.activity.NoViewModelActivity
import com.zhalz.newz.databinding.ActivityMainBinding

class MainActivity : NoViewModelActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}