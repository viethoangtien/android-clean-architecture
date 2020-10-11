package com.soict.hoangviet.cleanarchitecture.ui.news

import androidx.lifecycle.MutableLiveData
import com.soict.hoangviet.data.models.base.ListResponse
import com.soict.hoangviet.domain.models.NewsDomain
import com.soict.hoangviet.domain.usecases.GetNewsUseCase
import com.soict.hoangviet.cleanarchitecture.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {
    var newsLiveData = MutableLiveData<ListResponse<NewsDomain>>()

    fun fetchNews() {
        compositeDisposable.add(
            getNewsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    newsLiveData.value = ListResponse.loading()
                }
                .subscribe({
                    newsLiveData.value = ListResponse.success(it as ArrayList<NewsDomain>)
                }, {
                    newsLiveData.value = ListResponse.error(it)
                })
        )
    }

}