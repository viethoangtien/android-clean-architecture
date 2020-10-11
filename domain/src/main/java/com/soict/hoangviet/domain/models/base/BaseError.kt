package com.soict.hoangviet.domain.models.base

class BaseError(message: String, val code: Int = 0, var isShowToast: Boolean = false) :
    Throwable(message) {
}