package com.soict.hoangviet.data.models.base

object Result {
    const val LOADING = 0
    const val SUCCESS = 1
    const val ERROR = 2

    object Message {
        const val ERROR_TRY_AGAIN = "Có lỗi xảy ra. Vui lòng thử lại."
        const val TIME_OUT = "Thời gian kết nối đã hết. Vui lòng thử lại."
    }
}