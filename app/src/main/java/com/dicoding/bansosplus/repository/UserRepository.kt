package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.UserResponse
import com.dicoding.bansosplus.pref.UserPreferences
import retrofit2.Response

class UserRepository(private val sessionManager: SessionManager) {
    suspend fun get(): Response<UserResponse> {
        return RetrofitInstance.userApi.get("Bearer " + sessionManager.fetchToken())
    }
}