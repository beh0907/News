package com.skymilk.news.domain.usecases.news

import com.skymilk.news.data.local.NewsDao
import com.skymilk.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}