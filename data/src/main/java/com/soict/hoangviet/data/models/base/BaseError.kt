package com.soict.hoangviet.data.models.base

class BaseError(message: String, val code: Int = 0, var isShowToast: Boolean = false) :
    Throwable(message) {
}