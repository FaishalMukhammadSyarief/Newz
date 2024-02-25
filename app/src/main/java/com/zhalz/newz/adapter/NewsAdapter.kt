package com.zhalz.newz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhalz.newz.data.NewsData
import com.zhalz.newz.databinding.ItemNewsBinding

class NewsAdapter : ListAdapter<NewsData, NewsAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.dataNews = getItem(position)
        Glide
            .with(holder.itemView.context)
            .load(getItem(position).imageUrl)
            .into(holder.binding.ivImage)
    }

    inner class UserViewHolder(var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsData>() {
            override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
                return oldItem == newItem
            }
        }
    }

}
