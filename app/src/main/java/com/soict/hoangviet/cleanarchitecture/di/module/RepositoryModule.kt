package com.soict.hoangviet.cleanarchitecture.di.module

import com.soict.hoangviet.data.apiservice.ApiService
import com.soict.hoangviet.data.di.NetworkModule
import com.soict.hoangviet.data.mappers.NewsMapper
import com.soict.hoangviet.data.repositories.NewsRepositoryImpl
import com.soict.hoangviet.domain.repositories.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideNewsRepository(
        apiService: ApiService,
        newsMapper: NewsMapper
    ): NewsRepository = NewsRepositoryImpl(apiService, newsMapper)
}