package com.dicoding.bansosplus.interfaces


import com.dicoding.bansosplus.models.auth.FeedbackRequest
import com.dicoding.bansosplus.models.auth.FeedbackResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface FeedbackApi {

    @GET("api/bansos/feedbacks")
    suspend fun getFeedbackByBansos(
        @Header("Authorization") token: String,
        @Body feedbackRequest: FeedbackRequest
    ): Response<FeedbackResponse>

}