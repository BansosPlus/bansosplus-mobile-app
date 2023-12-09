package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.google.gson.annotations.SerializedName

data class BansosResponse(
    @field:SerializedName("data")
    val data: List<BansosItem>? = null,
)