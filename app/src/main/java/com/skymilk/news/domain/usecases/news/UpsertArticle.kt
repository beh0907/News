package com.skymilk.news.domain.usecases.news

import com.skymilk.news.data.local.NewsDao
import com.skymilk.news.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article)
    }
}