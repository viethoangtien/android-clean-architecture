package com.soict.hoangviet.cleanarchitecture.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment

inline fun <reified T : ViewModel> DaggerAppCompatActivity.injectViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}

inline fun <reified T : ViewModel> AppCompatActivity.injectViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}

inline fun <reified T : ViewModel> DaggerFragment.injectViewModel(
    fragmentActivity: FragmentActivity? = null,
    viewModelFactory: ViewModelProvider.Factory
): T {
    return fragmentActivity?.let {
        ViewModelProviders.of(it, viewModelFactory).get(T::class.java)
    } ?: kotlin.run{
        ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
    }
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.injectParentViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(parentFragment!!, viewModelFactory).get(T::class.java)
}