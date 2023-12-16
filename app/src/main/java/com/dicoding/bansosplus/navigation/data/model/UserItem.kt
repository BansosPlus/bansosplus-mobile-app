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

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("income")
    val income: String,

    @field:SerializedName("wall_quality")
    val wallQuality: String,

    @field:SerializedName("number_of_meals")
    val numberOfMeals: String,

    @field:SerializedName("fuel")
    val fuel: String,

    @field:SerializedName("education")
    val education: String,

    @field:SerializedName("total_asset")
    val totalAsset: String,

    @field:SerializedName("treatment")
    val treatment: String,

    @field:SerializedName("number_of_dependents")
    val numberOfDependents: String,
)
