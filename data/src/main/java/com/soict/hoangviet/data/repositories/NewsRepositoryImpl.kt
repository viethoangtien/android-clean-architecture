package com.soict.hoangviet.data.repositories

import com.soict.hoangviet.data.apiservice.ApiService
import com.soict.hoangviet.data.mappers.ListNewsMapper
import com.soict.hoangviet.data.mappers.NewsMapper
import com.soict.hoangviet.data.models.NewsEntity
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse
import com.soict.hoangviet.domain.models.NewsDomain
import com.soict.hoangviet.domain.repositories.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val listNewsMapper: ListNewsMapper
) : NewsRepository {

    override fun getNews(): Single<ListLoadMoreResponse<NewsDomain>> {
        return apiService.getListNews(pageIndex = 1, pageSize = 10)
            .map {
                return@map listNewsMapper.mapToDomain(it)
            }
    }
}