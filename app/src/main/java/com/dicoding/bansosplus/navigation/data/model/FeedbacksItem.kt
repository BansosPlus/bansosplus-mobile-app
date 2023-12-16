package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName

data class FeedbacksItem(
    @field:SerializedName("feedback_id")
    val feedbackId: Int,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("bansos_id")
    val bansosId: Int,

    @field:SerializedName("score")
    val score: Int,

    @field:SerializedName("description")
    val description: String,

)
