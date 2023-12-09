package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class BansosItem(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("expiry_date")
    val expiryDate: Date,

    @field:SerializedName("image_url")
    val imageUrl: String,
)