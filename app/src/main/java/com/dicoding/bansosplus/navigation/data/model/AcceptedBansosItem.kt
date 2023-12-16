package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AcceptedBansosItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("expiry_date")
    val expiryDate: Date,

    @field:SerializedName("updated_at")
    val updatedAt: Date,

    @field:SerializedName("created_at")
    val createdAt: Date,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("image_url")
    val imageUrl: String,
    )
