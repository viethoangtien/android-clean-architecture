package com.soict.hoangviet.cleanarchitecture.ui.news

import android.util.Log
import androidx.lifecycle.Observer
import com.soict.hoangviet.cleanarchitecture.R
import com.soict.hoangviet.cleanarchitecture.databinding.FragmentNewsBinding
import com.soict.hoangviet.cleanarchitecture.di.annotation.LayoutId
import com.soict.hoangviet.cleanarchitecture.extensions.injectViewModel
import com.soict.hoangviet.cleanarchitecture.ui.base.BaseFragment

@LayoutId(R.layout.fragment_news)
class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    private lateinit var newsViewModel: NewsViewModel

    override fun backFromAddFragment() {

    }

    override fun backPressed(): Boolean {
        return true
    }

    override fun initView() {
    }

    override fun initViewModel() {
        newsViewModel = injectViewModel(viewModelFactory)
    }

    override fun initData() {
        newsViewModel.fetchNews()
    }

    override fun initListener() {
        newsViewModel.newsLiveData.observe(this, Observer {
            it?.let {
                handleListResponse(it)
            }
        })
    }

    override fun getListResponse(data: MutableList<*>?, isRefresh: Boolean, canLoadMore: Boolean) {
        Log.d("myLog", data.toString())
    }
}