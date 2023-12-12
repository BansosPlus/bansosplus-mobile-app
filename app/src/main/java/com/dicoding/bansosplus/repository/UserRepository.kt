package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response

class UserRepository(private val sessionManager: SessionManager) {
    suspend fun get(): Response<UserResponse> {
        return RetrofitInstance.userApi.get("Bearer " + sessionManager.fetchToken())
    }

    suspend fun update(multipartBody: MultipartBody.Part): Response<UserResponse> {
        return RetrofitInstance.userApi.update("Bearer " + sessionManager.fetchToken(), multipartBody)
    }
}