package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName

data class BansosRegistrationItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("bansos_id")
    val bansosId: Int,

    @field:SerializedName("status")
    val status: String,
)
