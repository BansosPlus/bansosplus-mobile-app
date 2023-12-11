package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.BansosStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BansosRegistrationApi {
    @GET("api/users/bansos-registration")
    suspend fun getBansosRegistrationByUser(@Header("Authorization") token: String): Response<BansosStatusResponse>
}