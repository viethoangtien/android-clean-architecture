package com.soict.hoangviet.data.models.base

import com.google.gson.annotations.SerializedName

open class ListLoadMoreResponse<T>(
    val type: Int,
    @SerializedName("response")
    val data: BaseListResponse<T>?,
    val error: Throwable?,
    var isRefresh: Boolean = false,
    var isLoadingMore: Boolean = false
) : BaseResponse() {
    companion object {
        fun <T> loading(): ListLoadMoreResponse<T> =
            ListLoadMoreResponse(Result.LOADING, null, null)

        fun <T> success(data: ArrayList<T>): ListLoadMoreResponse<T> =
            ListLoadMoreResponse(Result.SUCCESS, BaseListResponse(data = data), null)

        fun <T> error(throwable: Throwable): ListLoadMoreResponse<T> =
            ListLoadMoreResponse(Result.ERROR, null, throwable)
    }
}

fun <T> ListLoadMoreResponse<T>.setLoadingMore(
    isRefresh: Boolean = false,
    isLoadingMore: Boolean = false
): ListLoadMoreResponse<T> {
    this.isRefresh = isRefresh
    this.isLoadingMore = isLoadingMore
    return this
}