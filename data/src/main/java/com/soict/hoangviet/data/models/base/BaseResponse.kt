package com.soict.hoangviet.data.models.base

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status")
    var status: Int = 0

    @SerializedName("msg")
    var msg: String? = ""

    @SerializedName("result")
    var result: Int = 0

    @SerializedName("errormsg")
    var errorMsg: String ?= ""
}