package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName

data class ValidateRegisItem(
    @field:SerializedName("bansos_registration_id")
    val bansosRegistrationId: Int,

    @field:SerializedName("bansos_id")
    val bansosId: Int,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("status")
    val status: String,
)
