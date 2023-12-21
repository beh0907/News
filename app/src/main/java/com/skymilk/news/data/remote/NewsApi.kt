package com.skymilk.news.data.remote

import com.skymilk.news.BuildConfig
import com.skymilk.news.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit2 API
interface NewsApi {

    //전체 뉴스 목록 가져오기
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int, // 페이징
        @Query("sources") sources: String, // 출처
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY // api키
    ): NewsResponse

    //키워드 검색 뉴스 목록 가져오기
    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY // api키
    ): NewsResponse
}