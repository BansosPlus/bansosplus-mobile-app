package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.FeedbacksItem
import com.google.gson.annotations.SerializedName

data class FeedbacksResponse(
    @field:SerializedName("data")
    val data: FeedbacksItem? = null,
)