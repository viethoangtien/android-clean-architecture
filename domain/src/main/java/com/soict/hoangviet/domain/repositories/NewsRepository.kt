package com.soict.hoangviet.domain.repositories

import com.soict.hoangviet.domain.models.NewsDomain
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse
import io.reactivex.Single

interface NewsRepository {
    fun getNews(): Single<ListLoadMoreResponse<NewsDomain>>
}