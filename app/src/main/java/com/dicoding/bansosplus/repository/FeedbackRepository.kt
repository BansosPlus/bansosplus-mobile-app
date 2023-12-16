package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.FeedbackRequest
import com.dicoding.bansosplus.models.auth.FeedbackResponse
import com.dicoding.bansosplus.models.auth.FeedbacksResponse
import com.dicoding.bansosplus.models.auth.RegisterRequest
import retrofit2.Response

class FeedbackRepository(private val sessionManager: SessionManager) {
    suspend fun getFeedbackByBansos(id: String): Response<FeedbackResponse> {
        val token = sessionManager.fetchToken()
        if (token != null) {
            val authorizationHeader = "Bearer $token"
            return RetrofitInstance.feedbackApi.getFeedbackByBansos(token = authorizationHeader, bansosId = id)
        } else {
            throw IllegalStateException("Token is null. Please login.")
        }
    }
    suspend fun addFeedback(request: FeedbackRequest): Response<FeedbacksResponse> {
        val token = sessionManager.fetchToken()
        if (token != null) {
            val authorizationHeader = "Bearer $token"
            return RetrofitInstance.feedbackApi.addFeedback(token = authorizationHeader, request)
        } else {
            throw IllegalStateException("Token is null. Please login.")
        }
    }

}