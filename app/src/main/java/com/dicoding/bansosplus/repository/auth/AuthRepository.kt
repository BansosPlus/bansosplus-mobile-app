package com.dicoding.bansosplus.repository.auth

import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.LoginRequest
import com.dicoding.bansosplus.models.auth.LoginResponse
import retrofit2.Response

class AuthRepository {
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return RetrofitInstance.authApi.login(request)
    }
}