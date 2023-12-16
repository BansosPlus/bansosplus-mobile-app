package com.dicoding.bansosplus.models.auth

import com.dicoding.bansosplus.navigation.data.model.AcceptedBansosItem
import com.google.gson.annotations.SerializedName

data class AcceptedBansosResponse(
    @field:SerializedName("data")
    val data: AcceptedBansosItem? = null,
)
