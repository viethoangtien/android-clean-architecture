package com.soict.hoangviet.cleanarchitecture.ui.news

import androidx.lifecycle.MutableLiveData
import com.soict.hoangviet.cleanarchitecture.base.BaseViewModel
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse
import com.soict.hoangviet.domain.models.NewsDomain
import com.soict.hoangviet.domain.usecases.GetNewsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {
    var newsLiveData = MutableLiveData<ListLoadMoreResponse<NewsDomain>>()

    fun fetchNews() {
        compositeDisposable.add(
            getNewsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    newsLiveData.value = ListLoadMoreResponse.loading()
                }
                .subscribe({
                    newsLiveData.value = ListLoadMoreResponse.success(it.data?.data as ArrayList<NewsDomain>)
                }, {
                    newsLiveData.value = ListLoadMoreResponse.error(it)
                })
        )
    }

}