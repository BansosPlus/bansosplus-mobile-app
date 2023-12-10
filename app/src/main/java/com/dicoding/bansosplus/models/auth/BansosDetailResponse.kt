package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.google.gson.annotations.SerializedName

data class BansosDetailResponse(
    @field:SerializedName("data")
    val data: BansosItem? = null,
)