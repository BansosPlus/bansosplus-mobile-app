package com.dicoding.bansosplus.models.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FeedbackRequest {
    @SerializedName("bansos_id")
    @Expose
    var bansosId: Int? = null

    @SerializedName("score")
    @Expose
    var score: Int? = null

    @SerializedName("description")
    @Expose
    var description: String? = null
}