package com.soict.hoangviet.data.mappers

import com.soict.hoangviet.data.models.EntityModel
import com.soict.hoangviet.domain.models.DomainModel

abstract class DataMapper<DM : DomainModel, EM : EntityModel> {
    abstract fun mapToEntity(domainModel: DM): EM

    abstract fun mapToDomain(entityModel: EM): DM
}