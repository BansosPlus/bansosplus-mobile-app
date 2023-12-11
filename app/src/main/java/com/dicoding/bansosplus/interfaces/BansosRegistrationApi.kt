package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.BansosRegistrationRequest
import com.dicoding.bansosplus.models.auth.BansosRegistrationResponse
import com.dicoding.bansosplus.models.auth.BansosStatusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BansosRegistrationApi {
    @POST("api/bansos-registration")
    suspend fun registerBansos(@Header("Authorization") token: String, @Body bansosRegistrationRequest: BansosRegistrationRequest): Response<BansosRegistrationResponse>
    @GET("api/users/bansos-registration")
    suspend fun getBansosRegistrationByUser(@Header("Authorization") token: String): Response<BansosStatusResponse>
}