package com.skymilk.news.data.remote

import com.skymilk.news.BuildConfig
import com.skymilk.news.data.remote.dto.NewsResponse
import com.skymilk.news.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit2 API
interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int, // 페이징
        @Query("sources") sources: String, // 출처
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY // api키
    ): NewsResponse

}