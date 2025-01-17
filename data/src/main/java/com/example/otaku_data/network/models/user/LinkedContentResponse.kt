package com.example.otaku_data.network.models.user

import com.google.gson.annotations.SerializedName

data class LinkedContentResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("russian") var russian: String? = null,
    @SerializedName("image") var image: ImageResponse? = null,
    @SerializedName("episodes") var episodes: Int? = null
)