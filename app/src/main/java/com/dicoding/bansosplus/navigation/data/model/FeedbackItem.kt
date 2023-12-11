package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName

data class FeedbackItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("user_name")
    val userName: String,

    @field:SerializedName("bansos_id")
    val bansosId: Int,

    @field:SerializedName("score")
    val score: Int,

    @field:SerializedName("description")
    val description: String,

    )
