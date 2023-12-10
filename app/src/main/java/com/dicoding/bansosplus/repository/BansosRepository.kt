package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.BansosResponse
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import retrofit2.Response

class BansosRepository(private val sessionManager: SessionManager) {
    suspend fun get(): Response<BansosResponse> {
        return RetrofitInstance.bansosApi.get("Bearer " + sessionManager.fetchToken())
    }
    suspend fun getDetail(id: String): Response<BansosItem> {
        val token = sessionManager.fetchToken()
        if (token != null) {
            // Token is available, make the network request
            val authorizationHeader = "Bearer $token"
            return RetrofitInstance.bansosApi.getDetail(authorizationHeader, id)
        } else {
            // Token is not available, handle the error or request a new token
            // Example: throw an exception
            throw IllegalStateException("Token is null. Please login.")
        }
    }
}