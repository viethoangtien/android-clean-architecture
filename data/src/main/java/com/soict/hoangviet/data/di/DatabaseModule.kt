package com.soict.hoangviet.data.di

import android.content.Context
import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import com.soict.hoangviet.data.db.AppSharePreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideSharePreferences(context: Context): SharePreference = AppSharePreference(context)
}