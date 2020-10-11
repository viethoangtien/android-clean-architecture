package com.soict.hoangviet.domain.usecases

import com.soict.hoangviet.domain.models.NewsDomain
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse
import io.reactivex.Single

interface GetNewsUseCase {
    fun execute(): Single<ListLoadMoreResponse<NewsDomain>>
}