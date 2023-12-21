package com.skymilk.news.presentation.bookmark

import com.skymilk.news.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)