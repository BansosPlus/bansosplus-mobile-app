package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.BansosDetailResponse
import com.dicoding.bansosplus.models.auth.BansosResponse
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import retrofit2.Response

class BansosRepository(private val sessionManager: SessionManager) {
    suspend fun get(): Response<BansosResponse> {
        return RetrofitInstance.bansosApi.get("Bearer " + sessionManager.fetchToken())
    }
    suspend fun getDetail(id: String): Response<BansosDetailResponse> {
        val token = sessionManager.fetchToken()
        if (token != null) {
            val authorizationHeader = "Bearer $token"
            return RetrofitInstance.bansosApi.getDetail(authorizationHeader, id)
        } else {
            throw IllegalStateException("Token is null. Please login.")
        }
    }
}