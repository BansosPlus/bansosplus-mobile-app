package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.LoginRequest
import com.dicoding.bansosplus.models.auth.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>
}