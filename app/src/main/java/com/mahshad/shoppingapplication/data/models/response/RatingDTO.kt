package com.mahshad.shoppingapplication.data.models.response

import com.google.gson.annotations.SerializedName

data class RatingDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Double?
)