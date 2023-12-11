package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.LoginRequest
import com.dicoding.bansosplus.models.auth.LoginResponse
import com.dicoding.bansosplus.models.auth.RegisterRequest
import com.dicoding.bansosplus.models.auth.RegisterResponse
import retrofit2.Response

class AuthRepository {
    suspend fun register(request: RegisterRequest): Response<RegisterResponse> {
        return RetrofitInstance.authApi.register(request)
    }
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return RetrofitInstance.authApi.login(request)
    }
}