package com.skymilk.news.presentation.details

import com.skymilk.news.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveMessage : DetailsEvent()

}