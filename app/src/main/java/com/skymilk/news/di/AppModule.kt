package com.skymilk.news.di

import android.app.Application
import com.skymilk.news.data.manager.LocalUserManagerImpl
import com.skymilk.news.data.remote.NewsApi
import com.skymilk.news.data.repository.NewsRepositoryImpl
import com.skymilk.news.domain.manager.LocalUserManager
import com.skymilk.news.domain.repository.NewsRepository
import com.skymilk.news.domain.usecases.appEntry.AppEntryUseCases
import com.skymilk.news.domain.usecases.appEntry.ReadAppEntry
import com.skymilk.news.domain.usecases.appEntry.SaveAppEntry
import com.skymilk.news.domain.usecases.news.GetNews
import com.skymilk.news.domain.usecases.news.NewsUseCases
import com.skymilk.news.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        ReadAppEntry(localUserManager),
        SaveAppEntry(localUserManager)
    )


    //NewsApi 의존성 주입
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) = NewsUseCases(
        GetNews(newsRepository)
    )
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
}