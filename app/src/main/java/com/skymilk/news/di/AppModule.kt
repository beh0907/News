package com.skymilk.news.di

import android.app.Application
import com.skymilk.news.data.manager.LocalUserManagerImpl
import com.skymilk.news.domain.manager.LocalUserManager
import com.skymilk.news.domain.usecases.AppEntryUseCases
import com.skymilk.news.domain.usecases.ReadAppEntry
import com.skymilk.news.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}