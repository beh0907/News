package com.skymilk.news.domain.usecases.news

import com.skymilk.news.data.local.NewsDao
import com.skymilk.news.domain.model.Article
import com.skymilk.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}