package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.BansosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BansosApi {
    @GET("api/bansos")
    suspend fun getBansos(@Header("Authorization") token: String): Response<BansosResponse>
}