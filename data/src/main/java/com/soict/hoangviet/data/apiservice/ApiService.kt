package com.soict.hoangviet.data.apiservice

import com.soict.hoangviet.data.models.NewsEntity
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("listNews")
    fun getListNews(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): Single<ListLoadMoreResponse<NewsEntity>>

}