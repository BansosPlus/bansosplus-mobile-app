package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.LoginRequest
import com.dicoding.bansosplus.models.auth.LoginResponse
import com.dicoding.bansosplus.models.auth.RegisterRequest
import com.dicoding.bansosplus.models.auth.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}