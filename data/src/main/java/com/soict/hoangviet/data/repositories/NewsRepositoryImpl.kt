package com.soict.hoangviet.data.repositories

import com.soict.hoangviet.data.apiservice.ApiService
import com.soict.hoangviet.data.mappers.NewsMapper
import com.soict.hoangviet.domain.models.NewsDomain
import com.soict.hoangviet.domain.repositories.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsMapper: NewsMapper
) : NewsRepository {

    override fun getNews(): Single<List<NewsDomain>> {
        return apiService.getListNews(pageIndex = 1, pageSize = 10)
            .map {
                return@map it.map { newsMapper.mapToDomain(it) }
            }
    }
}