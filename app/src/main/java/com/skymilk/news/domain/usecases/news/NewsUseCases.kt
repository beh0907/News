package com.skymilk.news.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val getArticle: GetArticle,
    val getArticles: GetArticles,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
)
