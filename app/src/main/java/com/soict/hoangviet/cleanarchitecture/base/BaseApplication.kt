package com.soict.hoangviet.cleanarchitecture.base

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.soict.hoangviet.cleanarchitecture.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {
    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}