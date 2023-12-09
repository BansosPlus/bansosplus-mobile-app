package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName

data class UserItem(
    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("token")
    val token: String,
)
