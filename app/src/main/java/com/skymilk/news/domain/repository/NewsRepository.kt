package com.skymilk.news.domain.repository

import androidx.paging.PagingData
import com.skymilk.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    //전체 뉴스 목록
    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    //검색어 뉴스 목록
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    //뉴스 기사 북마크 목록
    fun getArticles(): Flow<List<Article>>

    //뉴스 기사
    suspend fun getArticle(url: String): Article?

    //뉴스 기사 북마크 추가
    suspend fun upsertArticle(article: Article)

    //뉴스 기사 북마크 해제
    suspend fun deleteArticle(article: Article)
}