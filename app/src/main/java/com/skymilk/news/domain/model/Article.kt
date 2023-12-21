package com.skymilk.news.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//각 뉴스별 정보 객체
@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    //각 기사의 고유 URL을 기본키
    @PrimaryKey val url: String,
    val urlToImage: String
)