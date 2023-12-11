package com.dicoding.bansosplus.models.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BansosRegistrationRequest {
    @SerializedName("bansos_id")
    @Expose
    var bansosId: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("nik")
    @Expose
    var nik: String? = null

    @SerializedName("nokk")
    @Expose
    var noKk: String? = null
}