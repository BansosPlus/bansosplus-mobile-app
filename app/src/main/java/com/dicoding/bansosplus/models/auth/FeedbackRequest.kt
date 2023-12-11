package com.dicoding.bansosplus.models.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeedbackRequest(
    @SerializedName("bansos_id")
    @Expose
    var bansosId: Int? = null
)
