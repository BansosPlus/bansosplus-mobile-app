package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class BansosStatusItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("bansos_name")
    val bansosName: String,

    @field:SerializedName("created_at")
    val createdAt: Date,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("image_url")
    val imageUrl: String,
    )
