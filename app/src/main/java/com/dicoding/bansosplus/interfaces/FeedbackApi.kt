package com.dicoding.bansosplus.interfaces


import com.dicoding.bansosplus.models.auth.FeedbackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FeedbackApi {

    @GET("api/bansos/feedbacks")
    suspend fun getFeedbackByBansos(
        @Header("Authorization") token: String,
        @Query("bansos_id") bansosId: String
    ): Response<FeedbackResponse>

}