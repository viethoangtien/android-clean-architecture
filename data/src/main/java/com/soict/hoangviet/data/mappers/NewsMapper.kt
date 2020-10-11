package com.soict.hoangviet.data.mappers

import com.soict.hoangviet.data.models.NewsEntity
import com.soict.hoangviet.domain.models.NewsDomain
import javax.inject.Inject

class NewsMapper @Inject constructor() : DataMapper<NewsDomain, NewsEntity>() {
    override fun mapToEntity(domainModel: NewsDomain): NewsEntity {
        TODO("Not yet implemented")
    }

    override fun mapToDomain(entityModel: NewsEntity): NewsDomain {
        return NewsDomain(
            id = entityModel.id,
            description = entityModel.description
        )
    }
}