package com.soict.hoangviet.domain.usecases

import com.soict.hoangviet.domain.models.NewsDomain
import io.reactivex.Single

interface GetNewsUseCase {
    fun execute(): Single<List<NewsDomain>>
}