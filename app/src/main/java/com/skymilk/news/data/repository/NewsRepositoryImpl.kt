package com.skymilk.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.skymilk.news.data.remote.NewsApi
import com.skymilk.news.data.remote.NewsPagingSource
import com.skymilk.news.data.remote.SearchNewsPagingSource
import com.skymilk.news.domain.model.Article
import com.skymilk.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi,
                    sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery,
                    newsApi,
                    sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}