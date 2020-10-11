package com.soict.hoangviet.cleanarchitecture.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soict.hoangviet.cleanarchitecture.ui.main.MainViewModel
import com.soict.hoangviet.cleanarchitecture.ui.news.NewsViewModel
import com.soict.hoangviet.mvvmarchitecture.di.viewmodel.ViewModelFactory
import com.soict.hoangviet.mvvmarchitecture.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
}