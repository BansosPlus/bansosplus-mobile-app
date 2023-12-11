package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.BansosStatusItem
import com.google.gson.annotations.SerializedName

data class BansosStatusResponse(
    @field:SerializedName("data")
    val data: List<BansosStatusItem>? = null,
)
