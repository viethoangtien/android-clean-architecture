package com.soict.hoangviet.data.apiservice

import com.soict.hoangviet.domain.models.base.Result
import java.net.HttpURLConnection

class ApiException : Exception {
    var statusCode: Int = HttpURLConnection.HTTP_OK

    constructor() : super(Result.Message.ERROR_TRY_AGAIN)

    constructor(statusCode: Int) : super(Result.Message.ERROR_TRY_AGAIN) {
        this.statusCode = statusCode
    }

    constructor(msg: String) : super(msg)

    constructor(statusCode: Int, msg: String) : super(msg) {
        this.statusCode = statusCode
    }
}