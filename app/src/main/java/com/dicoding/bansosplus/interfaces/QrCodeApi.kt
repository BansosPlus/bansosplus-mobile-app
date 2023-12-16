package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.QrCodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface QrCodeApi {
    @GET("api/qr-codes/show")
    suspend fun getQrCode(
        @Header("Authorization") token: String,
        @Query("bansos_registration_id") bansosRegistrationId: String
    ): Response<QrCodeResponse>
}