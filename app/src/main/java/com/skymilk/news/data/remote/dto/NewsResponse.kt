package com.skymilk.news.data.remote.dto

import com.skymilk.news.domain.model.Article

//뉴스 API 응답 객체
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)