package com.soict.hoangviet.domain.usecases.impl

import com.soict.hoangviet.domain.models.NewsDomain
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse
import com.soict.hoangviet.domain.repositories.NewsRepository
import com.soict.hoangviet.domain.usecases.GetNewsUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) : GetNewsUseCase {

    override fun execute(): Single<ListLoadMoreResponse<NewsDomain>> {
        return newsRepository.getNews()
    }
}