package com.zhalz.newz.data

import com.crocodic.core.api.ModelResponse
import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("results")
	val data: List<NewsData>?

) : ModelResponse()

data class NewsData(

	@field:SerializedName("country")
	val country: List<String?>? = null,

	@field:SerializedName("sentiment")
	val sentiment: String? = null,

	@field:SerializedName("creator")
	val creator: List<String?>? = null,

	@field:SerializedName("keywords")
	val keywords: List<String?>? = null,

	@field:SerializedName("sentiment_stats")
	val sentimentStats: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("ai_region")
	val aiRegion: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("pubDate")
	val pubDate: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("source_url")
	val sourceUrl: String? = null,

	@field:SerializedName("article_id")
	val articleId: String? = null,

	@field:SerializedName("source_icon")
	val sourceIcon: String? = null,

	@field:SerializedName("video_url")
	val videoUrl: Any? = null,

	@field:SerializedName("source_priority")
	val sourcePriority: Int? = null,

	@field:SerializedName("ai_tag")
	val aiTag: String? = null,

	@field:SerializedName("source_id")
	val sourceId: String? = null,

	@field:SerializedName("category")
	val category: List<String?>? = null
)
