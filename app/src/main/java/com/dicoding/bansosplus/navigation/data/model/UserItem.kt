package com.dicoding.bansosplus.navigation.data.model

import com.google.gson.annotations.SerializedName

data class UserItem(
    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("nik")
    val nik: String,

    @field:SerializedName("no_kk")
    val noKk: String,

    @field:SerializedName("income")
    val income: String,

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("image_url")
    val imageUrl: String,
)
