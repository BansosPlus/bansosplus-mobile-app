package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.UserItem
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data")
    val data: UserItem? = null,
)
