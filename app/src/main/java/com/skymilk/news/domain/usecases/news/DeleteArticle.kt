package com.skymilk.news.domain.usecases.news

import com.skymilk.news.data.local.NewsDao
import com.skymilk.news.data.remote.dto.NewsResponse
import com.skymilk.news.domain.model.Article
import com.skymilk.news.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}