package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.AcceptedBansosResponse
import com.dicoding.bansosplus.models.auth.BansosRegistrationRequest
import com.dicoding.bansosplus.models.auth.BansosRegistrationResponse
import com.dicoding.bansosplus.models.auth.BansosStatusResponse
import com.dicoding.bansosplus.models.auth.RegisStatusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface BansosRegistrationApi {
    @POST("api/bansos-registration")
    suspend fun registerBansos(@Header("Authorization") token: String, @Body bansosRegistrationRequest: BansosRegistrationRequest): Response<BansosRegistrationResponse>
    @GET("api/users/bansos-registration")
    suspend fun getBansosRegistrationByUser(
        @Header("Authorization") token: String,
        @Query("status") status: String
    ): Response<BansosStatusResponse>

    @PUT("api/bansos-registration/validate")
    suspend fun validateRegistration(
        @Header("Authorization") token: String,
        @Query("bansos_registration_id") bansosRegistrationId: String
    ): Response<RegisStatusResponse>

    @GET("api/bansos-registration")
    suspend fun getBansosRegistrationDetail(
        @Header("Authorization") token: String,
        @Query("bansos_registration_id") bansosRegistrationId: String
    ): Response<AcceptedBansosResponse>
}