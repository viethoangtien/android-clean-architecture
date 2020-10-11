package com.soict.hoangviet.cleanarchitecture.ui.main

import androidx.lifecycle.MutableLiveData
import com.soict.hoangviet.cleanarchitecture.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    var loadingLiveData = MutableLiveData<Boolean>()
}