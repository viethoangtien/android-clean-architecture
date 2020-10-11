package com.soict.hoangviet.data.apiservice

import com.google.gson.annotations.SerializedName
import com.soict.hoangviet.data.models.base.Result
import java.net.HttpURLConnection

class ApiError @JvmOverloads constructor(
    @SerializedName("msg")
    @JvmField
    var message: String = Result.Message.ERROR_TRY_AGAIN,
    @SerializedName("status")
    @JvmField
    var statusCode: Int = HttpURLConnection.HTTP_OK
) {
    fun getApiException(): ApiException {
        return ApiException(statusCode, message)
    }
}