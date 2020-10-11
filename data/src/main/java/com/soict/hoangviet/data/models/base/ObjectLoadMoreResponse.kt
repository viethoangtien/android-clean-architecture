package com.soict.hoangviet.data.models.base

class ObjectLoadMoreResponse<T> : ObjectResponse<T> {
    var isRefresh: Boolean = false
    var isLoadingMore: Boolean = false

    constructor(
        type: Int,
        data: T?,
        error: Throwable?
    ) : super(type, data, error)

    constructor(
        data: T?,
        isRefresh: Boolean,
        isLoadingMore: Boolean
    ) : super(Result.SUCCESS, data, null) {
        this.isRefresh = isRefresh
        this.isLoadingMore = isLoadingMore
    }

    companion object {
        fun <T> loading(): ObjectLoadMoreResponse<T> =
            ObjectLoadMoreResponse(
                Result.LOADING,
                data = null,
                error = null
            )

        fun <T> success(
            data: T?,
            isRefresh: Boolean = false,
            isLoadingMore: Boolean = false
        ): ObjectLoadMoreResponse<T> =
            ObjectLoadMoreResponse(
                data,
                isRefresh,
                isLoadingMore
            )

        fun <T> error(throwable: Throwable): ObjectLoadMoreResponse<T> =
            ObjectLoadMoreResponse(
                Result.ERROR,
                null,
                throwable
            )
    }
}