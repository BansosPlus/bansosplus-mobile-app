package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.QrCodeResponse
import retrofit2.Response

class QrCodeRepository(private val sessionManager: SessionManager) {
    suspend fun get(bansosRegistrationId: String): Response<QrCodeResponse> {
        val token = sessionManager.fetchToken()
        if (token != null) {
            val authorizationHeader = "Bearer $token"
            return RetrofitInstance.qrCodeApi.getQrCode(authorizationHeader, bansosRegistrationId)
        } else {
            throw IllegalStateException("Token is null. Please login.")
        }
    }
}