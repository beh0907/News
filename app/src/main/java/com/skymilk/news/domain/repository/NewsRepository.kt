package com.skymilk.news.domain.repository

import androidx.paging.PagingData
import com.skymilk.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    //전체 뉴스 목록
    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    //검색어 뉴스 목록
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
}