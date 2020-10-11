package com.soict.hoangviet.cleanarchitecture.ui.main

import android.view.View
import androidx.lifecycle.Observer
import com.soict.hoangviet.cleanarchitecture.R
import com.soict.hoangviet.cleanarchitecture.databinding.ActivityMainBinding
import com.soict.hoangviet.cleanarchitecture.di.annotation.LayoutId
import com.soict.hoangviet.cleanarchitecture.extensions.injectViewModel
import com.soict.hoangviet.cleanarchitecture.ui.base.BaseActivity
import com.soict.hoangviet.cleanarchitecture.ui.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

@LayoutId(R.layout.activity_main)
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mainViewModel: MainViewModel

    override fun getFragmentContainerId(): Int {
        return R.id.fl_container
    }

    override fun initViewModel() {
        mainViewModel = injectViewModel(viewModelFactory)
    }

    override fun initView() {
        viewController.addFragment(NewsFragment::class.java, null)
    }

    override fun initData() {

    }

    override fun initListener() {
        mainViewModel.loadingLiveData.observe(this, Observer { isShowLoading ->
            if (isShowLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    override fun showLoading() {
        if (!loading.isShown) {
            loading.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

}