package com.soict.hoangviet.data.models

import com.google.gson.annotations.SerializedName

data class NewsEntity(
    @field:SerializedName("feed")
    val feed: String? = null,

    @field:SerializedName("detail_url")
    val detailUrl: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("thumb_img")
    val thumbImg: String? = null,

    @field:SerializedName("publish_date")
    val publishDate: String? = null
) : EntityModel() {

}