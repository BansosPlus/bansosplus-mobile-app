package com.dicoding.bansosplus.repository

import android.util.Log
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.FeedbackRequest
import com.dicoding.bansosplus.models.auth.FeedbackResponse
import retrofit2.Response

class FeedbackRepository(private val sessionManager: SessionManager) {
    suspend fun getFeedbackByBansos(request: FeedbackRequest): Response<FeedbackResponse> {
        val token = sessionManager.fetchToken()
        if (token != null) {
            val authorizationHeader = "Bearer $token"
            return RetrofitInstance.feedbackApi.getFeedbackByBansos(authorizationHeader, request)
        } else {
            throw IllegalStateException("Token is null. Please login.")
        }
    }

}