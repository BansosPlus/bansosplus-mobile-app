package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName

data class FeedbackItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("user_id")
    val userId: String,

    @field:SerializedName("bansos_id")
    val bansosId: String,

    @field:SerializedName("score")
    val score: String,

    @field:SerializedName("description")
    val description: String,

    )
