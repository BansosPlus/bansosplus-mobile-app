package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ValidateRegisItem(
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("bansos_id")
    val bansosId: Int?,

    @field:SerializedName("user_id")
    val userId: Int?,

    @field:SerializedName("user_name")
    val userName: String?,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("created_at")
    val createdAt: Date?,

    @field:SerializedName("updated_at")
    val updatedAt: Date?,
)
