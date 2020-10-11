package com.soict.hoangviet.data.mappers

import com.soict.hoangviet.data.models.NewsEntity
import com.soict.hoangviet.domain.models.NewsDomain
import com.soict.hoangviet.domain.models.base.BaseListResponse
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse
import javax.inject.Inject

class ListNewsMapper @Inject constructor(private val newsMapper: NewsMapper) : ListDataMapper<NewsDomain, NewsEntity>() {
    override fun mapToEntity(domainModel: ListLoadMoreResponse<NewsDomain>): ListLoadMoreResponse<NewsEntity> {
        TODO("Not yet implemented")
    }

    override fun mapToDomain(entityModel: ListLoadMoreResponse<NewsEntity>): ListLoadMoreResponse<NewsDomain> {
        return ListLoadMoreResponse(
            type = entityModel.type,
            isLoadingMore = entityModel.isLoadingMore,
            isRefresh = entityModel.isRefresh,
            error = entityModel.error,
            data = BaseListResponse(
                totalPage = entityModel.data?.totalPage,
                totalItems = entityModel.data?.totalItems,
                currentPage = entityModel.data?.currentPage,
                data = entityModel.data?.data?.map {
                    newsMapper.mapToDomain(it)
                } as ArrayList<NewsDomain>?
            )
        )
    }

}