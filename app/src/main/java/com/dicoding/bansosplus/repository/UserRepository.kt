package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.UserRequest
import com.dicoding.bansosplus.models.auth.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response

class UserRepository(private val sessionManager: SessionManager) {
    suspend fun get(): Response<UserResponse> {
        return RetrofitInstance.userApi.get("Bearer " + sessionManager.fetchToken())
    }

    suspend fun updateImage(multipartBody: MultipartBody.Part): Response<UserResponse> {
        return RetrofitInstance.userApi.updateImage("Bearer " + sessionManager.fetchToken(), multipartBody)
    }

    suspend fun update(request: UserRequest): Response<UserResponse> {
        return RetrofitInstance.userApi.update("Bearer " + sessionManager.fetchToken(), request)
    }
}