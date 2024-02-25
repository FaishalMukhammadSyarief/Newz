package com.zhalz.newz.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object UrlImage {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadUrlString(imageUrl: String?) {
        Glide.with(context)
            .load(imageUrl)
            .into(this)
    }

}