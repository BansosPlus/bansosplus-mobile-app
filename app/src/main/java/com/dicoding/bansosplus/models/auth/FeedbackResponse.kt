package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.FeedbackItem
import com.google.gson.annotations.SerializedName

class FeedbackResponse {
    @field:SerializedName("data")
    val data: List<FeedbackItem>? = null
}