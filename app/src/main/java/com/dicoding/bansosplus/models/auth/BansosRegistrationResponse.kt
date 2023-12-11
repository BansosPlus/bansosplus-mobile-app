package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.BansosRegistrationItem
import com.google.gson.annotations.SerializedName

data class BansosRegistrationResponse(
    @field:SerializedName("data")
    val data: BansosRegistrationItem? = null,
)
