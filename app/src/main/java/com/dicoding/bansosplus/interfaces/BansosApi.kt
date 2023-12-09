package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.BansosResponse
import retrofit2.Response
import retrofit2.http.GET

interface BansosApi {
    @GET("api/bansos")
    suspend fun getBansos(): Response<BansosResponse>
}