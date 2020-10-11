package com.soict.hoangviet.cleanarchitecture.di.builder

import com.soict.hoangviet.cleanarchitecture.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity
}