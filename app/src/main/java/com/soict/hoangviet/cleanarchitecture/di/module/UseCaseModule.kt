package com.soict.hoangviet.cleanarchitecture.di.module

import android.app.Application
import android.content.Context
import com.soict.hoangviet.cleanarchitecture.di.module.RepositoryModule
import com.soict.hoangviet.domain.repositories.NewsRepository
import com.soict.hoangviet.domain.usecases.GetNewsUseCase
import com.soict.hoangviet.domain.usecases.impl.GetNewsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    internal fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase = GetNewsUseCaseImpl(newsRepository)
}