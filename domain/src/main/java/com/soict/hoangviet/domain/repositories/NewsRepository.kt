package com.soict.hoangviet.domain.repositories

import com.soict.hoangviet.domain.models.NewsDomain
import io.reactivex.Single

interface NewsRepository {
    fun getNews(): Single<List<NewsDomain>>
}