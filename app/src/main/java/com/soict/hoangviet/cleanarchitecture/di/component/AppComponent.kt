package com.soict.hoangviet.cleanarchitecture.di.component

import android.app.Application
import android.content.Context
import com.soict.hoangviet.cleanarchitecture.di.builder.ActivityBuilder
import com.soict.hoangviet.cleanarchitecture.di.builder.FragmentBuilder
import com.soict.hoangviet.baseproject.di.module.AppModule
import com.soict.hoangviet.cleanarchitecture.di.module.UseCaseModule
import com.soict.hoangviet.cleanarchitecture.base.BaseApplication
import com.soict.hoangviet.cleanarchitecture.di.module.RepositoryModule
import com.soict.hoangviet.data.di.DatabaseModule
import com.soict.hoangviet.cleanarchitecture.di.viewmodel.ViewModelModule
import com.soict.hoangviet.data.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        (AndroidInjectionModule::class),
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (DatabaseModule::class),
        (NetworkModule::class),
        (ActivityBuilder::class),
        (UseCaseModule::class),
        (RepositoryModule::class),
        (FragmentBuilder::class),
        (ViewModelModule::class)]
)
@Singleton
interface AppComponent {
    fun getContext(): Context
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(baseApplication: BaseApplication)
}