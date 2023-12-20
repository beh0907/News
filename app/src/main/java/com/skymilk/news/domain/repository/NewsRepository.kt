package com.skymilk.news.domain.repository

import androidx.paging.PagingData
import com.skymilk.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}