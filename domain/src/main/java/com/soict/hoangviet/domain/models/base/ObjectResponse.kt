package com.soict.hoangviet.domain.models.base

open class ObjectResponse<T> (
    val type: Int,
    val data: T?,
    val error: Throwable?
) : BaseResponse() {
    companion object {
        fun <T> loading(): ObjectResponse<T> =
            ObjectResponse(
                Result.LOADING,
                null,
                null
            )

        fun <T> success(data: T): ObjectResponse<T> =
            ObjectResponse(
                Result.SUCCESS,
                data,
                null
            )

        fun <T> error(throwable: Throwable): ObjectResponse<T> =
            ObjectResponse(
                Result.ERROR,
                null,
                throwable
            )

    }
}