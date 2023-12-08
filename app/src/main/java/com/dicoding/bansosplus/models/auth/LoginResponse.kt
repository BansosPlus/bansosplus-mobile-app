package com.dicoding.bansosplus.models.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("data")
    @Expose
    var data: User? = null

    class User {
        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("token")
        @Expose
        var token: String? = null
    }
}