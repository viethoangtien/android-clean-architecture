package com.soict.hoangviet.data.mappers

import com.soict.hoangviet.data.models.EntityModel
import com.soict.hoangviet.domain.models.DomainModel
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse

abstract class ListDataMapper<DM : DomainModel, EM : EntityModel> {
    abstract fun mapToEntity(domainModel: ListLoadMoreResponse<DM>): ListLoadMoreResponse<EM>

    abstract fun mapToDomain(entityModel: ListLoadMoreResponse<EM>): ListLoadMoreResponse<DM>
}