package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.ValidateRegisItem
import com.google.gson.annotations.SerializedName

data class RegisStatusResponse(
    @field:SerializedName("data")
    val data: ValidateRegisItem? = null,
)
