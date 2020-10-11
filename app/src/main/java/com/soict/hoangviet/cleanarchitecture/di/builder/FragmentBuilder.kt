package com.soict.hoangviet.cleanarchitecture.di.builder

import com.soict.hoangviet.cleanarchitecture.ui.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector()
    abstract fun bindNewsFragment(): NewsFragment
}